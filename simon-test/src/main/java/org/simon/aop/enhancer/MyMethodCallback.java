package org.simon.aop.enhancer;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 你搞忘写注释了
 *
 * @author Admin
 * @Copyright © 2019 tiger Inc. All rights reserved.
 * @create 2019-09-21 16:51
 */
public class MyMethodCallback implements MethodInterceptor {
	@Override
	public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
		System.out.println("invoke method:"+method);
		return methodProxy.invokeSuper(o, objects);
	}
}
