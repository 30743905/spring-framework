package org.simon.ioc.beandifinitionreader;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.lang.annotation.Annotation;

/**
 * @author Administrator
 * @Copyright © 2019 tiger Inc. All rights reserved.
 * @create 2019-03-05 下午 22:46
 * @Description:TODO
 */
public class MyClassPathDefinitonScanner extends ClassPathBeanDefinitionScanner {
	private Class type;
	public MyClassPathDefinitonScanner(BeanDefinitionRegistry registry, Class<? extends Annotation> type){
		super(registry,false);
		this.type = type;
	}
	/**
	 * 注册 过滤器
	 */
	public void registerTypeFilter(){
		addIncludeFilter(new AnnotationTypeFilter(type));
	}
}