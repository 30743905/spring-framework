package org.simon.aop.demo3;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Administrator
 * @Copyright © 2019 tiger Inc. All rights reserved.
 * @create 2019-02-18 下午 23:32
 * @Description:TODO
 */
public class MainTest {

	@Test
	public void test1(){
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext("org.simon.aop.demo3");
		LoginService loginService = (LoginService) context.getBean("loginServiceImpl");
		System.out.println(loginService.getClass().getName());

		loginService.greetTo("zhangsan");
		loginService.serverTo("zhangsan");

		//System.out.println(context.getBean(LoginServiceImpl.class).getClass().getName());
		System.out.println(context.getBean(LoginService.class).getClass().getName());
	}

}
