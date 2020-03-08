package org.simon.mybatis;

import org.junit.Test;
import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 你搞忘写注释了
 *
 * @author Admin
 * @Copyright © 2019 tiger Inc. All rights reserved.
 * @create 2019-09-18 0:19
 */
public class TestMain {

	@Test
	public void t1(){
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MapperConfig.class);
		UserMapper userMapper = context.getBean("userMapper", UserMapper.class);
		System.out.println("userMapper:"+userMapper);
		userMapper.get(1000);
		userMapper.insert("zhangsan", 10);

	}

	@Test
	public void t2(){
		/*Reflections reflections
				= new Reflections(
						new ConfigurationBuilder()
								.setUrls(ClasspathHelper.forPackage(scanPackage))
								.setScanners(new MethodAnnotationsScanner()));*/

	}
}
