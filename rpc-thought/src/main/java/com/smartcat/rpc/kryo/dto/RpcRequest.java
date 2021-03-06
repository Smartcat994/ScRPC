package com.smartcat.rpc.kryo.dto;

import lombok.*;

/**
 * description: RpcRequest
 * date: 2020/10/27 14:52
 *
 * @author: 张哲珲
 * version: 1.0.0
 */
@AllArgsConstructor
@Getter
@NoArgsConstructor
@Builder
@ToString
public class RpcRequest {
    private String interfaceName;
    private String methodName;
}
