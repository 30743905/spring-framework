package org.simon.ioc.demo2;

import org.junit.Test;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Administrator
 * @Copyright © 2019 tiger Inc. All rights reserved.
 * @create 2019-03-14 下午 23:52
 * @Description:TODO
 */
public class Demo1 {

	@Test
	public void test1(){
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext("org.simon.ioc.demo2");
		System.out.println(context.getBean(MyService.class));


	}

}
