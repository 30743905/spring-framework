package org.simon.aop.demo3;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
		loginService.test("ss", 10, "fjafoa");

		//System.out.println(context.getBean(LoginServiceImpl.class).getClass().getName());
		System.out.println(context.getBean(LoginService.class).getClass().getName());

		Logger logger = LoggerFactory.getLogger(MainTest.class);
		logger.info("abc111");

		//org.apache.commons.logging.impl.SLF4JLog ab = null;
	}

}
