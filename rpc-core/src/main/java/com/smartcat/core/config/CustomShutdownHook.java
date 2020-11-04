package com.smartcat.core.config;

import com.smartcat.common.util.CuratorUtils;
import com.smartcat.common.util.ThreadPoolFactoryUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * description: CustomShutdownHook
 * date: 2020/11/4 8:58
 * 自定义用户关闭触发器
 * 例如手动关闭服务
 * @author: SmartCat
 * version: 1.0.0
 */
@Slf4j
public class CustomShutdownHook {
    private static final CustomShutdownHook CUSTOM_SHUTDOWN_HOOK =new CustomShutdownHook();

    public static CustomShutdownHook getCustomShutdownHook(){
        return CUSTOM_SHUTDOWN_HOOK;
    }

    /**
     * jvm中增加一个关闭的钩子，当jvm关闭的时候，会执行系统中已经设置的所有通过方法addShutdownHook添加的钩子
     * 就是结束之前可以做一些事情来保证关闭的时候不会造成服务的影响
     */
    public void clearAll(){
        log.info("clearAll..............");
        Runtime.getRuntime().addShutdownHook(
                new Thread(() ->{
                    //清除所有的心跳
                    CuratorUtils.clearRegistry(CuratorUtils.getZkClient());
                    //关闭所有的线程
                    ThreadPoolFactoryUtils.shutDownAllThreadPool();
                })
        );
    }
}
