package com.smartcat.core.registry.myzk;

import com.smartcat.core.loadbalance.LoadBalance;
import com.smartcat.core.registry.ServiceDiscovery;

import java.net.InetSocketAddress;

/**
 * description: ZkServiceDiscovery
 * date: 2020/10/29 16:31
 *
 * @author: 张哲珲
 * version: 1.0.0
 */
public class ZkServiceDiscovery implements ServiceDiscovery {


    private final LoadBalance loadBalance;

    public ZkServiceDiscovery(LoadBalance loadBalance) {
        this.loadBalance = loadBalance;
    }

    @Override
    public InetSocketAddress lookupService(String rpcServiceName) {
        return null;
    }
}
