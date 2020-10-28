package com.smartcat.rpc.kryo.dto;

import lombok.*;

/**
 * description: RpcResponse
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
public class RpcResponse {
    private String message;
}
