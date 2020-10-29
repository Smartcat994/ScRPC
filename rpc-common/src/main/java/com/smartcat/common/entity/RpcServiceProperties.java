package com.smartcat.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * description: RpcServiceProperties
 * date: 2020/10/29 15:50
 *
 * @author: 张哲珲
 * version: 1.0.0
 */


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RpcServiceProperties {
    /**
     * service version
     */
    private String version;
    /**
     * when the interface has multiple implementation classes, distinguish by group
     */
    private String group;
    private String serviceName;

    public String toRpcServiceName() {
        return this.getServiceName() + this.getGroup() + this.getVersion();
    }
}