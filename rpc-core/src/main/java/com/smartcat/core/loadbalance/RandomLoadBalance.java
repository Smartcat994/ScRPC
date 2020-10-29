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

    /**
     * 随机返回一个地址
     * 这边秀了一下技巧,其实不用抽象类的,单纯的大脑抽了
     * @param serviceAddresses 服务的地址
     * @return 服务地址
     */
    @Override
    protected String doSelect(List<String> serviceAddresses) {
        Random random = new Random();
        return serviceAddresses.get(random.nextInt(serviceAddresses.size()));
    }
}
