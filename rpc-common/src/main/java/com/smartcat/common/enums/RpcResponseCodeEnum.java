package com.smartcat.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * description: RpcResponseCodeEnum
 * date: 2020/10/29 15:47
 *
 * @author: 张哲珲
 * version: 1.0.0
 */

@AllArgsConstructor
@Getter
@ToString
public enum RpcResponseCodeEnum {
    SUCCESS(200, "The remote call is successful"),
    FAIL(500, "The remote call is fail");
    private final int code;

    private final String message;
}
