package org.simon.aop.enhancer;

import org.junit.Test;
import org.springframework.cglib.core.SpringNamingPolicy;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * {@link org.springframework.context.annotation.ConfigurationClassEnhancer}
 * {@link EnableAspectJAutoProxy}
 *
 * @author Admin
 * @Copyright © 2019 tiger Inc. All rights reserved.
 * @create 2019-09-21 16:54
 */
public class TestDemo {

	@Test
	public void test1(){
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(TestService1.class);
		enhancer.setNamingPolicy(SpringNamingPolicy.INSTANCE);
		enhancer.setCallback(new MyMethodCallback());
		TestService1 testService1 = (TestService1) enhancer.create();

		System.out.println(testService1.hello("zhangsan"));
	}
}
