package org.simon.ioc.importdemo;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 你搞忘写注释了
 *
 * @author Admin
 * @Copyright © 2019 tiger Inc. All rights reserved.
 * @create 2019-09-21 11:51
 */
public class TestMain {

	@Test
	public void test1(){
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
		System.out.println(context.getBean(Service1.class));
		System.out.println(context.getBean(MyConfig.class));
		System.out.println(context.getBean(Service2.class));
		System.out.println(context.getBean(MyImportConfig.class));
	}
}
