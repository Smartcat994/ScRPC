package com.smartcat.rpc.kryo.serialize;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.smartcat.rpc.kryo.dto.RpcRequest;
import com.smartcat.rpc.kryo.dto.RpcResponse;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * description: KryoSerializer
 * date: 2020/10/27 14:35
 *
 * @author: 张哲珲
 * version: 1.0.0
 */
public class KryoSerializer implements Serializer {

    private static final ThreadLocal<Kryo> KRYO_THREAD_LOCAL =ThreadLocal.withInitial(()->{
        Kryo kryo = new Kryo();
        kryo.register(RpcResponse.class);
        kryo.register(RpcRequest.class);
        //默认值为true,是否关闭注册行为,关闭之后可能存在序列化问题，一般推荐设置为 true
        // 配置成 false 的话，序列化速度更快，但是遇到循环引用，就会报 “栈内存溢出” 错误。
        kryo.setReferences(true);
        //默认值为false,是否关闭循环引用，可以提高性能，但是一般不推荐设置为 true
        kryo.setRegistrationRequired(false);
        return kryo;
    });


    @Override
    public byte[] serialize(Object object) {
        //字节流缓冲区，默认32位
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             //Kryo内的方法
             Output output = new Output(byteArrayOutputStream)) {
            Kryo kryo = KRYO_THREAD_LOCAL.get();
            // Object->byte:将对象序列化为byte数组
            kryo.writeObject(output, object);
            KRYO_THREAD_LOCAL.remove();
            return output.toBytes();
        } catch (Exception e) {
            throw new RuntimeException("序列化失败");
        }
    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> clazz) {
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
             Input input = new Input(byteArrayInputStream)) {
            Kryo kryo = KRYO_THREAD_LOCAL.get();
            // byte->Object:从byte数组中反序列化出对对象
            Object o = kryo.readObject(input, clazz);
            KRYO_THREAD_LOCAL.remove();
            return clazz.cast(o);
        } catch (Exception e) {
            throw new RuntimeException("反序列化失败");
        }
    }
}
