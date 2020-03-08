package org.simon.beanfactoryPostProcessor.demo1;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.stereotype.Component;

/**
 * 你搞忘写注释了
 *
 * @author 张张/36410
 * @Copyright © 2019 tiger Inc. All rights reserved.
 * @create 2019-08-17 14:54
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@MyComponent
public @interface MyService {

}
