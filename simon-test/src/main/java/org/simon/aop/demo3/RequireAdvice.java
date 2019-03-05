package org.simon.aop.demo3;

import org.springframework.stereotype.Indexed;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Administrator
 * @Copyright © 2019 tiger Inc. All rights reserved.
 * @create 2019-02-18 下午 23:50
 * @Description:TODO
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequireAdvice {
}
