package org.simon.ioc.beandifinitionreader;

import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.beans.factory.support.SimpleBeanDefinitionRegistry;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;

import java.util.Arrays;
import java.util.Set;

/**
 * @author Administrator
 * @Copyright © 2019 tiger Inc. All rights reserved.
 * @create 2019-03-05 下午 22:16
 * @Description:TODO
 */
public class TestMain {

	/**
	 * 从Property配置文件加载BeanDefinition
	 */
	@Test
	public void propertiesBeanDefinitionReader() {
		//定义BeanDefinitionRegistry
		BeanDefinitionRegistry registry = new SimpleBeanDefinitionRegistry();
		//使用BeanDefinitionRegistry实例 构造BeanDefinitionReader
		PropertiesBeanDefinitionReader reader = new PropertiesBeanDefinitionReader(registry);
		//载入文件
		reader.loadBeanDefinitions("config.properties");

		System.out.println(registry.getBeanDefinition("mybean"));
	}

	/**
	 * 从xml配置文件加载BeanDefinition
	 */
	@Test
	public void xmlBeanDefinitionReader() {
		//定义BeanDefinitionRegistry
		BeanDefinitionRegistry registry = new SimpleBeanDefinitionRegistry();
		//使用BeanDefinitionRegistry实例 构造BeanDefinitionReader
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(registry);
		//载入文件
		reader.loadBeanDefinitions("spring.xml");

		System.out.println(registry.getBeanDefinition("testService"));
	}

	/**
	 * 使用AnnotatedBeanDefinitionReader.register()注册的获取到的是AnnotatedGenericBeanDefinition类型
	 */
	@Test
	public void annotatedBeanDefinitionReader(){
		SimpleBeanDefinitionRegistry registry= new SimpleBeanDefinitionRegistry();
		AnnotatedBeanDefinitionReader reader = new AnnotatedBeanDefinitionReader(registry);
		reader.register(TestService.class);
		System.out.println( registry.getBeanDefinitionCount());
		Arrays.stream(registry.getBeanDefinitionNames()).forEach(x -> {
			System.out.println(x+"::"+registry.getBeanDefinition(x).getClass().getName());
		});
	}

	/**
	 * ClassPathBeanDefinitionScanner 继承自ClassPathScanningCandidateComponentProvider
	 * ClassPathScanningCandidateComponentProvider:这个类的findCandidateComponents可以扫描到
	 * @Component @Repository @Service @Controller 的BeanDefinition，并加入Set 集合中
	 */
	@Test
	public void classPathScanningCandidateComponentProvider(){
		//ClassPathScanningCandidateComponentProvider
		ClassPathScanningCandidateComponentProvider provider=new ClassPathScanningCandidateComponentProvider(true);
		Set<BeanDefinition> definitionSet= provider.findCandidateComponents("org.simon.ioc.beandifinitionreader");
		definitionSet.stream().forEach(System.out::println);
	}

	@Test
	public void testSimpleScan() {
		String BASE_PACKAGE = "org.simon.ioc.beandifinitionreader";
		SimpleBeanDefinitionRegistry registry= new SimpleBeanDefinitionRegistry();
		MyClassPathDefinitonScanner myClassPathDefinitonScanner = new MyClassPathDefinitonScanner(registry, MyComponent.class);
		// 注册过滤器
		myClassPathDefinitonScanner.registerTypeFilter();
		int beanCount = myClassPathDefinitonScanner.scan(BASE_PACKAGE);
		String[] beanDefinitionNames = registry.getBeanDefinitionNames();
		System.out.println(beanCount);
		for (String beanDefinitionName : beanDefinitionNames) {
			System.out.println(beanDefinitionName);
		}
	}

}
