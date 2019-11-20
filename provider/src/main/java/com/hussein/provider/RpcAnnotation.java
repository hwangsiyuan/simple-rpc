package com.hussein.provider;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>Title: RpcAnnotation</p>
 * <p>Description: RPC注解</p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/11/18 3:03 PM
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RpcAnnotation {

    /**
     * 对外发布服务的接口
     *
     * @return 服务接口
     */
    Class<?> value();

    /**
     * 版本号
     *
     * @return 版本号
     */
    String version() default "";
}
