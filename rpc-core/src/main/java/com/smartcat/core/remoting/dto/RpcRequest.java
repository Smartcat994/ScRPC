package com.smartcat.core.remoting.dto;

import com.smartcat.common.entity.RpcServiceProperties;
import lombok.*;

import java.io.Serializable;

/**
 * description: RpcRequest
 * date: 2020/10/29 15:49
 *
 * @author: 张哲珲
 * version: 1.0.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
public class RpcRequest implements Serializable {
    private static final long serialVersionUID = 1905122041950251207L;
    private String requestId;
    private String interfaceName;
    private String methodName;
    private Object[] parameters;
    private Class<?>[] paramTypes;
    private String version;
    private String group;

    public RpcServiceProperties toRpcProperties() {
        return RpcServiceProperties.builder().serviceName(this.getInterfaceName())
                .version(this.getVersion())
                .group(this.getGroup()).build();
    }
}
