package com.smartcat.common.exception;

/**
 * description: RpcConfigEnum
 * date: 2020/10/29 17:07
 *
 * @author: 张哲珲
 * version: 1.0.0
 */
public enum RpcConfigEnum {
    RPC_CONFIG_PATH("rpc.properties"),
    ZK_ADDRESS("rpc.zookeeper.address");

    private final String propertyValue;


    RpcConfigEnum(String propertyValue) {
        this.propertyValue = propertyValue;
    }

    public String getPropertyValue() {
        return propertyValue;
    }
}
