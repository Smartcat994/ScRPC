package com.smartcat.rpc.kryo.serialize;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * description: NettyKryoDecoder
 * date: 2020/10/27 15:08
 * 这是一个解码器，用来把消息转化成需要的业务对象
 * @author: 张哲珲
 * version: 1.0.0
 */

@AllArgsConstructor
@Slf4j
public class NettyKryoDecoder extends ByteToMessageDecoder {

    /**
     * 序列化对象
     */
    private final Serializer serializer;

    /**
     * 类型
     */
    private final Class<?> genericClass;

    /**
     * Netty传输的消息长度也就是对象序列化后对应的字节数组的大小，存储在 ByteBuf 头部
     */
    private static final int BODY_LENGTH = 4;


    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> out) throws Exception {
        //1.byteBuf中写入的消息长度所占的字节数已经是4了，所以 byteBuf 的可读字节必须大于 4，
        if (in.readableBytes() >= BODY_LENGTH) {

            //2.标记当前readIndex的位置，以便后面重置readIndex 的时候使用
            in.markReaderIndex();

            //3.读取消息的长度
            int dataLength = in.readInt();
            //4.遇到不合理的情况直接 return
            if (dataLength < 0 || in.readableBytes() < 0) {
                log.error("data length or byteBuf readableBytes is not valid");
                return;
            }
            //5.如果可读字节数小于消息长度的话，说明是不完整的消息，重置readIndex
            if (in.readableBytes() < dataLength) {
                in.resetReaderIndex();
                return;
            }
            // 6.序列化
            byte[] body = new byte[dataLength];
            in.readBytes(body);
            // 将bytes数组转换为我们需要的对象
            Object obj = serializer.deserialize(body, genericClass);
            out.add(obj);
            log.info("successful decode ByteBuf to Object");
        }

    }
}
