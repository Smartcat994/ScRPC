package com.smartcat.core.compress;

/**
 * description: compress
 * date: 2020/10/29 9:58
 * 将序列化过后的byte[] 再次压缩为更小的byte[]
 * @author: 张哲珲
 * version: 1.0.0
 */
public interface Compress {

    /**
     * byte[] 进行压缩
     * @param bytes 序列化后的byte[]
     * @return 压缩后的byte[]
     */
    byte[] compress (byte[] bytes);

    /**
     * 进行解压
     * @param bytes 压缩后的byte[]
     * @return 解压后的byte[]
     */
    byte[] decompress (byte[] bytes);

}
