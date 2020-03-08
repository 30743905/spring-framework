package org.simon.aop.demo04;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.ClassUtils;

import java.util.Set;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Admin
 * @Copyright © 2020 tiger Inc. All rights reserved.
 * @create 2020-03-07 15:31
 */
@Slf4j
public class TestMain {

	@Test
	public void test01(){
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Cap10MainConfig.class);
		Calculator calculator = context.getBean(Calculator.class);
		calculator.div(10, 2);
	}

	@Test
	public void test02(){
		Set<Class<?>> set = ClassUtils.getAllInterfacesForClassAsSet(Calculator.class);
		log.info("set:{}", set);
	}
}