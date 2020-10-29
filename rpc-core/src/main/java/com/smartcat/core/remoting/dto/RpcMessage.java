package com.smartcat.core.remoting.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * description: RpcMessage
 * date: 2020/10/29 16:02
 *
 * @author: 张哲珲
 * version: 1.0.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RpcMessage {

    //rpc message type
    private byte messageType;

    //serialization type
    private byte codec;

    //compress type
    private byte compress;

    //request id
    private int requestId;

    //request data
    private Object data;

}
