package com.smartcat.core.remoting.transport.netty.client;

import com.smartcat.core.registry.ServiceDiscovery;
import com.smartcat.core.remoting.dto.RpcRequest;
import com.smartcat.core.remoting.transport.ClientTransport;

/**
 * description: NettyClientTransport
 * date: 2020/11/2 15:32
 *
 * @author: 张哲珲
 * version: 1.0.0
 */
public class NettyClientTransport implements ClientTransport {
    /**
     * 服务发现，引用了zk 干嘛不用
     */
    private final ServiceDiscovery serviceDiscovery;
    /**
     * 未处理的请求类
     */
    private final UnprocessedRequests unprocessedRequests;
    /**
     * 手动通道缓存
     */
    private final ChannelProvider channelProvider;

    public NettyClientTransport(ServiceDiscovery serviceDiscovery, UnprocessedRequests unprocessedRequests, ChannelProvider channelProvider) {
        this.serviceDiscovery = serviceDiscovery;
        this.unprocessedRequests = unprocessedRequests;
        this.channelProvider = channelProvider;
    }

    @Override
    public Object sendRpcRequest(RpcRequest rpcRequest) {
        return null;
    }
}
