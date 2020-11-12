package com.smartcat.common.util;

/**
 * description: Holder
 * date: 2020/11/9 9:20
 *
 * @author: SmartCat
 * version: 1.0.0
 */
public class Holder<T> {
    private volatile T value;

    public T get() {
        return value;
    }

    public void set(T value) {
        this.value = value;
    }
}
