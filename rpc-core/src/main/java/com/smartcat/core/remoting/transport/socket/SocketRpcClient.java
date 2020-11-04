package com.smartcat.core.remoting.transport.socket;

import com.smartcat.core.registry.ServiceDiscovery;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * description: SocketRpcClient
 * date: 2020/11/3 15:35
 *
 * @author: SmartCat
 * version: 1.0.0
 */
@AllArgsConstructor
@Slf4j
public class SocketRpcClient {
    private final ServiceDiscovery serviceDiscovery;
}
