package com.smartcat.common.exception;

import com.smartcat.common.enums.RpcErrorMessageEnum;

/**
 * description: RpcException
 * date: 2020/10/29 17:04
 *
 * @author: 张哲珲
 * version: 1.0.0
 */
public class RpcException extends RuntimeException {
    public RpcException(RpcErrorMessageEnum rpcErrorMessageEnum, String detail) {
        super(rpcErrorMessageEnum.getMessage() + ":" + detail);
    }

    public RpcException(String message, Throwable cause) {
        super(message, cause);
    }

    public RpcException(RpcErrorMessageEnum rpcErrorMessageEnum) {
        super(rpcErrorMessageEnum.getMessage());
    }
}
