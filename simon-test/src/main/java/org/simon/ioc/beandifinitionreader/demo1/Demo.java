package org.simon.ioc.beandifinitionreader.demo1;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * @author Admin
 * @Copyright © 2019 tiger Inc. All rights reserved.
 * @create 2019-08-14 23:06
 * @Description:TODO
 */
@Configuration
@ComponentScan("org.simon.ioc.beandifinitionreader")
public class Demo {

	@Test
	public void test1(){
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(Demo.class);
		context.refresh();

		System.out.println("================");
		Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);


	}

}
