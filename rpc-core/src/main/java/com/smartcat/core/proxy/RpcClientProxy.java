package com.smartcat.core.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * description: RpcClientProxy
 * date: 2020/10/29 15:25
 * Rpc的代理核心
 * @author: 张哲珲
 * version: 1.0.0
 */
public class RpcClientProxy implements InvocationHandler {

    private static final String INTERFACE_NAME = "interfaceName";

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return null;
    }
}
