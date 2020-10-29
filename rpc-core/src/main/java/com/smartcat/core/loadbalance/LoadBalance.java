package com.smartcat.core.loadbalance;

import java.util.List;

/**
 * description: LoadBalance
 * date: 2020/10/29 16:33
 * 自定义的负载均衡的接口
 * @author: 张哲珲
 * version: 1.0.0
 */
public interface LoadBalance {

    /**
     * 从List中选择一个地址
     * @param serviceAddresses 地址
     * @return 地址
     */
    String selectServiceAddress(List<String> serviceAddresses);

}
