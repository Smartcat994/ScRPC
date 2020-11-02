package com.smartcat.core.proxy;

import com.smartcat.common.entity.RpcServiceProperties;
import com.smartcat.core.remoting.transport.ClientTransport;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * description: RpcClientProxy
 * date: 2020/10/29 15:25
 * Rpc的代理核心
 * 动态代理，因为动态代理，所以才能做到如同本地调用一般的丝滑(不考虑网络传输)
 * @author: 张哲珲
 * version: 1.0.0
 */
public class RpcClientProxy implements InvocationHandler {

    /**
     * 默认接口名
     */
    private static final String INTERFACE_NAME = "interfaceName";

    private final ClientTransport clientTransport;
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

    public<T> T getProxy(Class<T> clazz){
        return (T)Proxy.newProxyInstance(clazz.getClassLoader(), new Class<?>[]{clazz}, this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return null;
    }
}
