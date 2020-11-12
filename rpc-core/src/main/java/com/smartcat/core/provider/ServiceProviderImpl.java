package com.smartcat.core.provider;

import com.smartcat.common.entity.RpcServiceProperties;
import com.smartcat.core.registry.ServiceRegistry;
import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * description: ServiceProviderImpl
 * date: 2020/11/4 10:03
 * 实现类
 * @author: SmartCat
 * version: 1.0.0
 */
@Slf4j
public class ServiceProviderImpl implements ServiceProvider {

    private final Map<String, Object> serviceMap;

    private final Set<String> registeredService;

    private final ServiceRegistry serviceRegistry;

   public ServiceProviderImpl(){
       serviceMap = new ConcurrentHashMap<>();
       registeredService = ConcurrentHashMap.newKeySet();
       serviceRegistry = ExtensionLoader.getExtensionLoader(ServiceRegistry.class).getExtension("zk");

   }

    @Override
    public void addService(Object service, Class<?> serviceClass, RpcServiceProperties rpcServiceProperties) {
        String rpcServiceName = rpcServiceProperties.toRpcServiceName();
        if (registeredService.contains(rpcServiceName)){
            return;
        }
        registeredService.add(rpcServiceName);
        serviceMap.put(rpcServiceName,service);
        log.info("Add service: {} and interfaces:{}", rpcServiceName, service.getClass().getInterfaces());
    }

    @Override
    public Object getService(RpcServiceProperties rpcServiceProperties) {
        return null;
    }

    @Override
    public void publishService(Object service, RpcServiceProperties rpcServiceProperties) {
        try {
            String host = InetAddress.getLocalHost().getHostAddress();
            Class<?> serviceRelatedInterface = service.getClass().getInterfaces()[0];
            String serviceName = serviceRelatedInterface.getCanonicalName();
            rpcServiceProperties.setServiceName(serviceName);
            this.addService(service, serviceRelatedInterface, rpcServiceProperties);
            serviceRegistry.registerService(rpcServiceProperties.toRpcServiceName(), new InetSocketAddress(host, NettyServer.PORT));
        } catch (UnknownHostException e) {
            log.error("occur exception when getHostAddress", e);
        }
    }

    @Override
    public void publishService(Object service) {
        this.publishService(service, RpcServiceProperties.builder().group("").version("").build());
    }
}
