package org.simon.ioc.instantiationAwareBeanPostProcessor;

import com.alibaba.fastjson.JSON;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Administrator
 * @Copyright © 2019 tiger Inc. All rights reserved.
 * @create 2019-04-04 下午 23:08
 * @Description:TODO
 */
public class TestDemo {

	@Test
	public void test1(){
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("org.simon.ioc.instantiationAwareBeanPostProcessor");

		PersonService personService = context.getBean(PersonService.class);
		System.out.println(JSON.toJSONString(personService, true));
	}
}
