package com.smartcat.core.provider;

import com.smartcat.common.entity.RpcServiceProperties;

/**
 * description: ServiceProvider
 * date: 2020/11/4 9:11
 * 存储和提供服务对象
 * @author: SmartCat
 * version: 1.0.0
 */
public interface ServiceProvider {
    /**
     * 添加服务
     * @param service 服务对象
     * @param serviceClass 服务类
     * @param rpcServiceProperties 服务相关属性
     */
    void addService(Object service, Class<?> serviceClass, RpcServiceProperties rpcServiceProperties);

    /**
     * 得到对应的服务对象
     * @param rpcServiceProperties
     * @return 服务的对象
     */
    Object getService(RpcServiceProperties rpcServiceProperties);

    /**
     * 发布服务
     * @param service 服务对象
     * @param rpcServiceProperties 服务相关属性
     */
    void publishService(Object service, RpcServiceProperties rpcServiceProperties);

    /**
     * 发布服务(属性)
     * @param service 服务对象
     */
    void publishService(Object service);
}
