package com.smartcat.core.compress.zip;

import com.smartcat.core.compress.Compress;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * description: ZipCompress
 * date: 2020/10/29 10:13
 *
 * @author: 张哲珲
 * version: 1.0.0
 */
@Slf4j
public class ZipCompress implements Compress {

    private static final int BUFFER_SIZE = 1024 * 4;

    @Override
    public byte[] compress(byte[] bytes) {

        if (bytes==null){
            throw  new NullPointerException("bytes is null");
        }

        try{
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            GZIPOutputStream gzip = new GZIPOutputStream(byteArrayOutputStream);
            //写入数据
            gzip.write(bytes);
            gzip.finish();
            log.info("压缩成功{}.",byteArrayOutputStream.toByteArray());
            return byteArrayOutputStream.toByteArray();
        }
        catch (IOException e) {
            log.error("压缩出现错误{}.",e.getMessage());
            throw new RuntimeException("gzip compress error", e);
        }
    }

    @Override
    public byte[] decompress(byte[] bytes) {

        if (bytes==null){
            throw  new NullPointerException("bytes is null");
        }

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try(GZIPInputStream gzipInputStream = new GZIPInputStream(new ByteArrayInputStream(bytes))){
            byte[] buffer = new byte[BUFFER_SIZE];
            int n ;
            while ((n = gzipInputStream.read(buffer)) > -1){
                byteArrayOutputStream.write(buffer,0,n);
            }
            return byteArrayOutputStream.toByteArray();

        } catch (IOException e) {
            log.error("解压出现错误{}.",e.getMessage());
            throw new RuntimeException("gzip compress error", e);
        }
    }

    public static void main(String[] args) {
        byte[] bytes = new byte[10000];
        for (int i = 0; i < bytes.length; i++) {
            for (int i1 = 0; i1 < 5; i1++) {
                bytes[i] = (byte) i1;
            }
        }


        ZipCompress zipCompress = new ZipCompress();
        byte[] compress = zipCompress.compress(bytes);
        for (byte b : compress) {
            System.out.println(b);
        }


        byte[] decompress = zipCompress.decompress(compress);
        for (byte b : decompress) {
            System.out.println(b);
        }
    }
}
