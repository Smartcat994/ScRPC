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

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, ByteBuf byteBuf) throws Exception {
        if (genericClass.isInstance(o) ){
            byte[] bytes = serializer.serialize(o);
            int length = bytes.length;
            byteBuf.writeInt(length);
            byteBuf.writeBytes(bytes);
        }

    }
}
