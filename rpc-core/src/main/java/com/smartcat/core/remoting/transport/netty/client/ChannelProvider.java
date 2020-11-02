package com.smartcat.core.remoting.transport.netty.client;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * description: ChannelProvider
 * date: 2020/11/2 15:57
 * 手动通道缓存
 * @author: 张哲珲
 * version: 1.0.0
 */
@Slf4j
public class ChannelProvider {
    private final Map<String, Channel> channelMap;
    private final NettyClient nettyClient;

    public ChannelFuture(){

    }


}
