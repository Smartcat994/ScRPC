package com.smartcat.core.registry;

import java.net.InetSocketAddress;

/**
 * description: ServiceRegistry
 * date: 2020/10/29 16:28
 *
 * @author: 张哲珲
 * version: 1.0.0
 */
public interface ServiceRegistry {

    /**
     * 进行服务的注册---->Zookeeper
     *
     * @param rpcServiceName  服务名字
     * @param inetSocketAddress 服务地址
     */
    void registerService(String rpcServiceName, InetSocketAddress inetSocketAddress);
}
