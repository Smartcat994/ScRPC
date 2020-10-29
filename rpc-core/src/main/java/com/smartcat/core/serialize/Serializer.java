package com.smartcat.core.serialize;

/**
 * description: Serializer
 * date: 2020/10/29 15:27
 * Rpc序列化接口
 * @author: 张哲珲
 * version: 1.0.0
 */
public interface Serializer {

    /**
     * 序列化
     *
     * @param obj 要序列化的对象
     * @return 字节数组
     */
    byte[] serialize(Object obj);

    /**
     * 反序列化
     *
     * @param bytes 序列化后的字节数组
     * @param clazz 目标类
     * @param <T>   类的类型。
     * @return 反序列化的对象
     */
    <T> T deserialize(byte[] bytes, Class<T> clazz);
}
