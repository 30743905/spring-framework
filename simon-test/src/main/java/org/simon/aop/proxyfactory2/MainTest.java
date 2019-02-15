package org.simon.aop.proxyfactory2;

import org.junit.Test;
import org.springframework.aop.framework.ProxyFactory;

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
}
