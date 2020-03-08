package org.simon.ioc.scanner;

import org.junit.Test;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.util.Arrays;

/**
 * @author Administrator
 * @Copyright © 2019 tiger Inc. All rights reserved.
 * @create 2019-04-05 下午 23:12
 * @Description:TODO
 */
public class ClassPathBeanDefinitionScannerTest {

	@Test
	public void test1(){
		GenericApplicationContext context = new GenericApplicationContext();
		ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(context);
		scanner.addIncludeFilter(new AnnotationTypeFilter(MyBean.class));


		int count = scanner.scan("org.simon.ioc.scanner");
		System.out.println("count:"+count);

		Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);

		context.refresh();
		System.out.println(context.getBean(MyService1.class));
		System.out.println(context.getBean(TestScannerBean.class));

	}
}
