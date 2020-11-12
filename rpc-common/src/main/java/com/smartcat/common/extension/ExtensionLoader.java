package com.smartcat.common.extension;

import com.smartcat.common.util.Holder;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * description: ExtensionLoader
 * date: 2020/11/9 9:14
 * 参考Dubbo的SPI
 * Java的SPI会初始化的时候拿到所有的资源
 * ExtensionLoader的作用就是加载所有打上了@SPI注解的接口，并根据配置进行实例化、封装
 * @author: SmartCat
 * version: 1.0.0
 */
public final class ExtensionLoader<T> {
    /**
     * 自定义硬编码加载文件的位置
     */
    private static final String SERVICE_DIRECTORY = "META-INF/extensions/";

    /**
     * 由于是一个并发的场景，所以需要使用JUC
     */
    private static final Map<Class<?>, ExtensionLoader<?>> EXTENSION_LOADERS = new ConcurrentHashMap<>();
    private static final Map<Class<?>, Object> EXTENSION_INSTANCES = new ConcurrentHashMap<>();


    private final Class<?> type;
    private final Map<String, Holder<Object>> cachedInstances = new ConcurrentHashMap<>();
    private final Holder<Map<String, Class<?>>> cachedClasses = new Holder<>();

    private ExtensionLoader(Class<?> type) {
        this.type = type;
    }

}
