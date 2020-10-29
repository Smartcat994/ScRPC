package com.smartcat.core.registry;

import java.net.InetSocketAddress;

/**
 * description: ServiceDiscovery
 * date: 2020/10/29 16:29
 *
 * @author: 张哲珲
 * version: 1.0.0
 */
public interface ServiceDiscovery {



    /**
     * 通过名字查询服务
     * @param rpcServiceName rpc服务名
     * @return service address
     */
    InetSocketAddress lookupService(String rpcServiceName);
}
