package org.simon.aop.proxyfactory;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author Administrator
 * @Copyright © 2019 tiger Inc. All rights reserved.
 * @create 2019-02-13 上午 0:24
 * @Description:TODO
 */
public class TestInteceptor implements MethodInterceptor {
	//要代理的对象实例
	private Object targetObject;

	public TestInteceptor(Object targetObject){
		this.targetObject = targetObject;
	}

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		return invocation.getMethod().invoke(this.targetObject, invocation.getArguments());
	}
}
