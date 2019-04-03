package org.simon.ioc.demo1;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author Administrator
 * @Copyright © 2019 tiger Inc. All rights reserved.
 * @create 2019-03-15 上午 0:32
 * @Description:TODO
 */
@Component
public class MyBeforeAdvice implements MethodBeforeAdvice {
	public void before(Method arg0, Object[] arg1, Object arg2) throws Throwable {
		System.out.println("-----洗手-----");
	}
}
