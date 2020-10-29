package com.smartcat.rpc.kryo.serialize;

/**
 * description: Serializer
 * date: 2020/10/27 15:37
 *
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
     * @param clazz 类
     * @param <T> 泛形
     * @return 反序列化的对象
     */
    <T> T deserialize(byte[] bytes, Class<T> clazz);
}
