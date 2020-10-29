package com.smartcat.core.loadbalance;

import java.util.List;

/**
 * description: AbstractLoadBalance
 * date: 2020/10/29 16:34
 *
 * @author: 张哲珲
 * version: 1.0.0
 */
public abstract class AbstractLoadBalance implements LoadBalance {

    @Override
    public String selectServiceAddress(List<String> serviceAddresses) {
        if (serviceAddresses == null || serviceAddresses.size() == 0) {
            return null;
        }
        if (serviceAddresses.size() == 1) {
            return serviceAddresses.get(0);
        }
        return doSelect(serviceAddresses);
    }

    protected abstract String doSelect(List<String> serviceAddresses);
}
