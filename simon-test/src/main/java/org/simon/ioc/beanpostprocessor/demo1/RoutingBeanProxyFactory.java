package org.simon.ioc.beanpostprocessor.demo1;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;

import java.util.Map;

/**
 * @author Administrator
 * @Copyright © 2019 tiger Inc. All rights reserved.
 * @create 2019-02-12 下午 22:10
 * @Description:TODO
 */
public class RoutingBeanProxyFactory {

	private final static String DEFAULT_BEAN_NAME = "helloServiceImpl1";

	public static Object createProxy(String name, Class type, Map<String, Object> candidates) {
		ProxyFactory proxyFactory = new ProxyFactory();
		proxyFactory.setInterfaces(type);
		proxyFactory.addAdvice(new VersionRoutingMethodInterceptor(name, candidates));
		return proxyFactory.getProxy();
	}

	static class VersionRoutingMethodInterceptor implements MethodInterceptor {
		private Object targetObject;

		public VersionRoutingMethodInterceptor(String name, Map<String, Object> beans) {
			this.targetObject = beans.get(name);
			if (this.targetObject == null) {
				this.targetObject = beans.get(DEFAULT_BEAN_NAME);
			}
		}

		@Override
		public Object invoke(MethodInvocation invocation) throws Throwable {
			System.out.println("before:"+System.currentTimeMillis());
			Object ret = invocation.getMethod().invoke(this.targetObject, invocation.getArguments());
			System.out.println("after:"+System.currentTimeMillis());
			return ret;
		}
	}
}