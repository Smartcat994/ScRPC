package com.smartcat.core.proxy;

import com.smartcat.common.entity.RpcServiceProperties;
import com.smartcat.common.enums.RpcErrorMessageEnum;
import com.smartcat.common.enums.RpcResponseCodeEnum;
import com.smartcat.common.exception.RpcException;
import com.smartcat.core.remoting.dto.RpcRequest;
import com.smartcat.core.remoting.dto.RpcResponse;
import com.smartcat.core.remoting.transport.ClientTransport;
import com.smartcat.core.remoting.transport.netty.client.NettyClientTransport;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * description: RpcClientProxy
 * date: 2020/10/29 15:25
 * Rpc的代理核心
 * 动态代理，因为动态代理，所以才能做到如同本地调用一般的丝滑(不考虑网络传输)
 * @author: 张哲珲
 * version: 1.0.0
 */
@Slf4j
public class RpcClientProxy implements InvocationHandler {

    /**
     * 默认接口名
     */
    private static final String INTERFACE_NAME = "interfaceName";
    /**
     * 客户端传输过程类
     */
    private final ClientTransport clientTransport;
    /**
     * RPC服务相关属性
     */
    private final RpcServiceProperties rpcServiceProperties;


    public RpcClientProxy(ClientTransport clientTransport) {
        this.clientTransport = clientTransport;
        this.rpcServiceProperties = RpcServiceProperties.builder().group("").version("").build();
    }

    public RpcClientProxy(ClientTransport clientTransport, RpcServiceProperties rpcServiceProperties) {
        this.clientTransport = clientTransport;
        if (rpcServiceProperties.getGroup() == null) {
            rpcServiceProperties.setGroup("");
        }
        if (rpcServiceProperties.getVersion() == null) {
            rpcServiceProperties.setVersion("");
        }
        this.rpcServiceProperties = rpcServiceProperties;
    }

    /**
     * 代理
     * @param clazz 需要代理的类对象
     * @param <T> 泛型
     * @return 代理后的地址
     */
    public<T> T getProxy(Class<T> clazz){
        return (T)Proxy.newProxyInstance(clazz.getClassLoader(), new Class<?>[]{clazz}, this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        log.info("invoked method: [{}]", method.getName());
        //构建请求代理类
        RpcRequest rpcRequest = RpcRequest.builder().methodName(method.getName())
                .parameters(args)
                .interfaceName(method.getDeclaringClass().getName())
                .paramTypes(method.getParameterTypes())
                .requestId(UUID.randomUUID().toString())
                .group(rpcServiceProperties.getGroup())
                .version(rpcServiceProperties.getVersion())
                .build();
        RpcResponse<Object> rpcResponse = null;
        //因为支持Socket和Netty两种 所以需要进行判断
        if (clientTransport instanceof NettyClientTransport) {
            CompletableFuture<RpcResponse<Object>> completableFuture = (CompletableFuture<RpcResponse<Object>>) clientTransport.sendRpcRequest(rpcRequest);
            rpcResponse = completableFuture.get();
        }
        if (clientTransport instanceof SocketRpcClient) {
            rpcResponse = (RpcResponse<Object>) clientTransport.sendRpcRequest(rpcRequest);
        }
        this.check(rpcResponse, rpcRequest);
        return rpcResponse.getData();
    }

    private void check(RpcResponse<Object> rpcResponse, RpcRequest rpcRequest) {
        if (rpcResponse == null) {
            throw new RpcException(RpcErrorMessageEnum.SERVICE_INVOCATION_FAILURE, INTERFACE_NAME + ":" + rpcRequest.getInterfaceName());
        }

        if (!rpcRequest.getRequestId().equals(rpcResponse.getRequestId())) {
            throw new RpcException(RpcErrorMessageEnum.REQUEST_NOT_MATCH_RESPONSE, INTERFACE_NAME + ":" + rpcRequest.getInterfaceName());
        }

        if (rpcResponse.getCode() == null || !rpcResponse.getCode().equals(RpcResponseCodeEnum.SUCCESS.getCode())) {
            throw new RpcException(RpcErrorMessageEnum.SERVICE_INVOCATION_FAILURE, INTERFACE_NAME + ":" + rpcRequest.getInterfaceName());
        }
    }
}
