package com.smartcat.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * description: SerializationTypeEnum
 * date: 2020/11/12 16:02
 *
 * @author: SmartCat
 * version: 1.0.0
 */
@AllArgsConstructor
@Getter
public enum SerializationTypeEnum {

    KYRO((byte) 0x01, "kyro");

    private final byte code;
    private final String name;

    public static String getName(byte code) {
        for (SerializationTypeEnum c : SerializationTypeEnum.values()) {
            if (c.getCode() == code) {
                return c.name;
            }
        }
        return null;
    }

}