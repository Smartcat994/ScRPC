package com.smartcat.core.remoting.handler;

import com.smartcat.common.util.CuratorUtils;
import com.smartcat.common.util.ThreadPoolFactoryUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * description: RpcRequestHandler
 * date: 2020/10/29 16:06
 *
 * @author: 张哲珲
 * version: 1.0.0
 */
@Slf4j
class CustomShutdownHook {
    private static final CustomShutdownHook CUSTOM_SHUTDOWN_HOOK = new CustomShutdownHook();

    public static CustomShutdownHook getCustomShutdownHook() {
        return CUSTOM_SHUTDOWN_HOOK;
    }

    public void clearAll() {
        log.info("addShutdownHook for clearAll");
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            CuratorUtils.clearRegistry(CuratorUtils.getZkClient());
            ThreadPoolFactoryUtils.shutDownAllThreadPool();
        }));
    }
}
