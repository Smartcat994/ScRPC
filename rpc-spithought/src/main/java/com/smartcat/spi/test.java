package com.smartcat.spi;

import java.util.ServiceLoader;

/**
 * description: test
 * date: 2020/11/4 8:51
 *
 * @author: SmartCat
 * version: 1.0.0
 */
public class test {
    public static void main(String[] args) {
        ServiceLoader<Demo> loader = ServiceLoader.load(Demo.class);
        for (Demo demo : loader) {
            demo.zzh();
        }
    }
}
