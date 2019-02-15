package org.simon.aop.proxyfactory2;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/**
 * @author Administrator
 * @Copyright © 2019 tiger Inc. All rights reserved.
 * @create 2019-02-14 下午 23:05
 * @Description:TODO
 */
public class AfterAdvice1 implements AfterReturningAdvice {

	public void afterReturning(Object returnValue, Method method,
							   Object[] args, Object target) throws Throwable {
		System.out.println("AfterAdvice1.afterReturning() execute ");
	}
}


