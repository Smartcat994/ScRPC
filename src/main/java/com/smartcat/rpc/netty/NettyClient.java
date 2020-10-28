package com.smartcat.rpc.netty;

import com.smartcat.rpc.kryo.dto.RpcRequest;
import com.smartcat.rpc.kryo.dto.RpcResponse;
import com.smartcat.rpc.kryo.serialize.KryoSerializer;
import com.smartcat.rpc.kryo.serialize.NettyKryoDecoder;
import com.smartcat.rpc.kryo.serialize.NettyKryoEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.AttributeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * description: NettyClient
 * date: 2020/10/28 8:45
 *
 * @author: 张哲珲
 * version: 1.0.0
 */
public class NettyClient {
    private static final Logger logger = LoggerFactory.getLogger(NettyClient.class);
    private final String host;
    private final int port;
    private static final Bootstrap b;

    public NettyClient(String host, int port) {
        this.host = host;
        this.port = port;
    }


    static {
        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        b = new Bootstrap();
        KryoSerializer kryoSerializer = new KryoSerializer();
        b.group(eventLoopGroup).channel(NioSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.ERROR))
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new NettyKryoDecoder(kryoSerializer, RpcResponse.class));
                        socketChannel.pipeline().addLast(new NettyKryoEncoder(kryoSerializer, RpcRequest.class));
                        socketChannel.pipeline().addLast(new NettyClientHandler());
                    }
                });
    }


    /**
     * 最关键的地方
     * 通过这个方法你可以将消息也就是RpcRequest 对象发送到服务端，
     * 并且你可以同步获取到服务端返回的结果也就是RpcResponse 对象。
     * @param rpcRequest 请求
     * @return 收到请求后返回的相应结果
     */
    public RpcResponse sendMessage(RpcRequest rpcRequest){
        try{
            //使用异步进行操作
            ChannelFuture f = b.connect(host, port).sync();
            logger.info("client connect  {}", host + ":" + port);
            Channel futureChannel = f.channel();
            logger.info("send message");
            if (futureChannel!=null){
                futureChannel.writeAndFlush(rpcRequest).addListener(future -> {
                    if (future.isSuccess()){
                        logger.info("client send message: [{}]", rpcRequest.toString());
                    }else {
                        logger.error("Send failed:", future.cause());
                    }
                });
                //阻塞等待 ，直到Channel关闭
                futureChannel.closeFuture().sync();
                // 将服务端返回的数据也就是RpcResponse对象取出
                AttributeKey<RpcResponse> key = AttributeKey.valueOf("rpcResponse");
                return futureChannel.attr(key).get();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
