package com.smartcat.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * description: CompressTypeEnum
 * date: 2020/11/12 16:01
 *
 * @author: SmartCat
 * version: 1.0.0
 */
@AllArgsConstructor
@Getter
public enum CompressTypeEnum {

    GZIP((byte) 0x01, "gzip");

    private final byte code;
    private final String name;

    public static String getName(byte code) {
        for (CompressTypeEnum c : CompressTypeEnum.values()) {
            if (c.getCode() == code) {
                return c.name;
            }
        }
        return null;
    }

}