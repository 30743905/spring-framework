package org.simon.ioc.beanpostprocessor.demo1;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 * @Copyright © 2019 tiger Inc. All rights reserved.
 * @create 2019-02-12 下午 22:11
 * @Description:TODO
 */

@Component
public class HelloServiceTest {

	@RountingInjected(value = "helloServiceImpl2")
	private HelloService helloService;

	public void testSayHello() {
		helloService.sayHello();
	}

	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext("org.simon.ioc.beanpostprocessor.demo1");
		HelloServiceTest helloServiceTest = applicationContext.getBean(HelloServiceTest.class);
		helloServiceTest.testSayHello();

	}
}