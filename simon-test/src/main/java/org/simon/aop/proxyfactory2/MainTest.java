package org.simon.aop.proxyfactory2;

import org.junit.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

import java.util.Arrays;

/**
 * @author Administrator
 * @Copyright © 2019 tiger Inc. All rights reserved.
 * @create 2019-02-14 下午 23:07
 * @Description:TODO
 */
public class MainTest {
	@Test
	public void proxyLoginTest() {
		ProxyFactory factory = new ProxyFactory();
		factory.addAdvice(new BeforeAdvice1());
		factory.addAdvice(new BeforeAdvice2());
		factory.addAdvice(new AfterAdvice1());
		factory.addAdvice(new AfterAdvice2());


		LoginService target = new LoginServiceImpl();
		factory.setTarget(target);
		/**
		 * proxyTargetClass=true强制使用cglib代理
		 * 默认是false，setInterfaces设置接口则使用jdk代理，否则cglib代理
		 */
		factory.setProxyTargetClass(false);
		factory.setInterfaces(new Class[]{LoginService.class});

		LoginService service = (LoginService) factory.getProxy();
		System.out.println("proxy type:"+service.getClass().getName());
		System.out.println(service.login(new User("xinchun.wang","123456")));
	}

	@Test
	public void test1() {
		ProxyFactory factory = new ProxyFactory();

		NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
		pointcut.addMethodName("test");
		DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, new BeforeAdvice1());
		factory.addAdvisor(advisor);

		LoginService target = new LoginServiceImpl();
		factory.setTarget(target);

		factory.setProxyTargetClass(true);
		//factory.setInterfaces(new Class[]{LoginService.class});

		LoginService service = (LoginService) factory.getProxy();
		System.out.println("proxy type:"+service.getClass().getName());
		Arrays.stream(service.getClass().getInterfaces()).forEach(x -> System.out.println("interface:"+x.getName()));
		System.out.println(service.getClass().getSuperclass());
		System.out.println(service.test(new User("xinchun.wang","123456")));
	}
}
