package com.smartcat.core.annotation;

import java.lang.annotation.*;

/**
 * description: RpcReference
 * date: 2020/10/29 9:49
 *
 * @author: 张哲珲
 * version: 1.0.0
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
@Inherited
public @interface RpcReference {

    /**
     * Service version, default value is empty string
     */
    String version() default "";

    /**
     * Service group, default value is empty string
     */
    String group() default "";

}
