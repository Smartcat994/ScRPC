package com.smartcat.core.registry.myzk;

import com.smartcat.common.util.CuratorUtils;
import com.smartcat.core.registry.ServiceRegistry;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;

import java.net.InetSocketAddress;

/**
 * description: ZkServiceRegistry
 * date: 2020/11/12 16:05
 *
 * @author: SmartCat
 * version: 1.0.0
 */
@Slf4j
public class ZkServiceRegistry implements ServiceRegistry {
    /**
     * ZK注册服务
     * @param rpcServiceName  服务名字
     * @param inetSocketAddress 服务地址
     */
    @Override
    public void registerService(String rpcServiceName, InetSocketAddress inetSocketAddress) {
        String servicePath = CuratorUtils.ZK_REGISTER_ROOT_PATH + "/" + rpcServiceName + inetSocketAddress.toString();
        CuratorFramework zkClient = CuratorUtils.getZkClient();
        CuratorUtils.createPersistentNode(zkClient, servicePath);
    }
}
