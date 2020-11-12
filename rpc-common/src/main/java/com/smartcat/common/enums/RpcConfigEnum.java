package com.smartcat.common.enums;

/**
 * description: RpcConfigEnum
 * date: 2020/11/12 16:01
 *
 * @author: SmartCat
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
