package org.simon.test1;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

/**
 * @author Administrator
 * @Copyright © 2019 tiger Inc. All rights reserved.
 * @create 2019-02-09 下午 22:17
 * @Description:TODO
 */
public class TestMain {

	@Test
	public void test1(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		Arrays.stream(ctx.getBeanDefinitionNames())
				.forEach(name -> {
					System.out.println("name:"+name+">>>"+((ClassPathXmlApplicationContext) ctx).getBeanFactory().getBeanDefinition(name).getClass().getName());
				});
		Object bean = ctx.getBean("testService");
		System.out.println("bean:"+bean);
	}
}
