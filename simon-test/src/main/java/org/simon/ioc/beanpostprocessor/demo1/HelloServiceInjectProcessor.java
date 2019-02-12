package org.simon.ioc.beanpostprocessor.demo1;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * @author Administrator
 * @Copyright © 2019 tiger Inc. All rights reserved.
 * @create 2019-02-12 下午 22:09
 * @Description:TODO
 */
@Component
public class HelloServiceInjectProcessor implements BeanPostProcessor {

	@Autowired
	private ApplicationContext applicationContext;

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		Class<?> targetCls = bean.getClass();
		Field[] targetFld = targetCls.getDeclaredFields();
		for (Field field : targetFld) {
			//找到制定目标的注解类
			if (field.isAnnotationPresent(RountingInjected.class)) {
				if (!field.getType().isInterface()) {
					throw new BeanCreationException("RoutingInjected field must be declared as an interface:" + field.getName()
							+ " @Class " + targetCls.getName());
				}
				try {
					this.handleRoutingInjected(field, bean, field.getType());
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		return bean;
	}

	/**
	 *
	 */
	private void handleRoutingInjected(Field field, Object bean, Class type) throws IllegalAccessException {
		Map<String, Object> candidates = this.applicationContext.getBeansOfType(type);
		field.setAccessible(true);
		if (candidates.size() == 1) {
			field.set(bean, candidates.values().iterator().next());
		} else if (candidates.size() == 2) {
			String injectVal = field.getAnnotation(RountingInjected.class).value();
			//方式1：采用代理方式，可对原始对象进行增强
			Object proxy = RoutingBeanProxyFactory.createProxy(injectVal, type, candidates);
			//方式2：如果不需要对原始对象进行增强，可直接从IoC中提取对应对象赋值
			//Object proxy = candidates.get(injectVal);
			field.set(bean, proxy);
		} else {
			throw new IllegalArgumentException("Find more than 2 beans for type: " + type);
		}

	}
}