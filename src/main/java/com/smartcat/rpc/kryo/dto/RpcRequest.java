package com.smartcat.rpc.kryo.dto;

import lombok.Data;

/**
 * description: RpcRequest
 * date: 2020/10/27 14:52
 *
 * @author: 张哲珲
 * version: 1.0.0
 */
@Data
public class RpcRequest {
    private String interfaceName;
    private String methodName;
}
