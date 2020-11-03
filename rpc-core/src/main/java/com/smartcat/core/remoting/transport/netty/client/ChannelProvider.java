package com.smartcat.core.remoting.transport.netty.client;

import com.smartcat.common.factory.SingletonFactory;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
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

    public ChannelProvider(){
        channelMap = new ConcurrentHashMap<>();
        nettyClient = SingletonFactory.getInstance(NettyClient.class);
    }

    public Channel get(InetSocketAddress inetSocketAddress){
        String key = inetSocketAddress.toString();
        //确定相应地址是否存在连接
        if (channelMap.containsKey(key)){
            Channel channel = channelMap.get(key);
            if (channel !=null&& channel.isActive()){
                return channel;
            }else {
                channelMap.remove(key);
            }
        }
        //否则，请重新连接以获取频道
        Channel channel = nettyClient.doConnect(inetSocketAddress);
        channelMap.put(key,channel);
        return channel;
    }

    public void remove(InetSocketAddress inetSocketAddress) {
        String key = inetSocketAddress.toString();
        channelMap.remove(key);
        log.info("Channel map size :[{}]", channelMap.size());
    }
}
