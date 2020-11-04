package com.smartcat.core.loadbalance;

import java.util.List;
import java.util.Random;

/**
 * description: RandomLoadBalance
 * date: 2020/10/29 16:37
 *
 * @author: 张哲珲
 * version: 1.0.0
 */
public class RandomLoadBalance extends AbstractLoadBalance {
    Random random = new Random();

    /**
     * 随机返回一个地址
     * @param serviceAddresses 服务的地址
     * @return 服务地址
     */
    @Override
    protected String doSelect(List<String> serviceAddresses) {
        return serviceAddresses.get(random.nextInt(serviceAddresses.size()));
    }
}
