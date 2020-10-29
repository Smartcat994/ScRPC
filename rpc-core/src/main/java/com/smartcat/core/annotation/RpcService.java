package com.smartcat.core.annotation;

import java.lang.annotation.*;

/**
 * description: RpcService
 * date: 2020/10/29 9:50
 *
 * @author: 张哲珲
 * version: 1.0.0
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
@Inherited
public @interface RpcService {

    /**
     *
     */
    String group() default "";

    /**
     * 服务的版本号
     */
    String version() default "";

}
