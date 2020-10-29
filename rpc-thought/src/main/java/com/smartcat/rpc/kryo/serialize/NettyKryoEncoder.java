package com.smartcat.rpc.kryo.serialize;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.AllArgsConstructor;

/**
 * description: NettyKryoEncoder
 * date: 2020/10/27 15:38
 * 编码器,将消息格式转换为字节数组然后写入到字节数据的容器 ByteBuf 对象中。
 * @author: 张哲珲
 * version: 1.0.0
 */
@AllArgsConstructor
public class NettyKryoEncoder extends MessageToByteEncoder {
    private final Serializer serializer;
    private final Class<?> genericClass;

    /**
     * 将对象转换为字节码然后写入到 ByteBuf 对象中
     */
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, ByteBuf byteBuf) {
        if (genericClass.isInstance(o)) {
            // 1. 将对象转换为byte
            byte[] body = serializer.serialize(o);
            // 2. 读取消息的长度
            int dataLength = body.length;
            // 3.写入消息对应的字节数组长度,writerIndex 加 4
            byteBuf.writeInt(dataLength);
            //4.将字节数组写入 ByteBuf 对象中
            byteBuf.writeBytes(body);
        }
    }
}
