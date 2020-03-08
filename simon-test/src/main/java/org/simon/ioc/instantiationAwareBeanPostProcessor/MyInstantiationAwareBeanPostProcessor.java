package org.simon.ioc.instantiationAwareBeanPostProcessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 * @Copyright © 2019 tiger Inc. All rights reserved.
 * @create 2019-04-04 下午 23:08
 * @Description:TODO
 */
@Component
public class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

	private Logger logger = LoggerFactory.getLogger(MyInstantiationAwareBeanPostProcessor.class);

	/**
	 * 如果该方法的返回值代替原本该生成的目标对象，后续只有postProcessAfterInitialization方法会调用，其它方法不调用；否则按照正常的流程走
	 */
	@Override
	public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
		if(beanClass == PersonService.class){
			logger.info("MyInstantiationAwareBeanPostProcessor -> postProcessBeforeInstantiation, beanClass:{}, beanName:{}",
					beanClass, beanName);
		}
		return null;
	}

	@Override
	public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
		if(bean.getClass() == PersonService.class){
			logger.info("MyInstantiationAwareBeanPostProcessor -> postProcessAfterInstantiation, bean:{}, beanName:{}",
					bean, beanName);
		}
		return true;
	}

	@Override
	public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
		if(bean.getClass() == PersonService.class){
			logger.info("MyInstantiationAwareBeanPostProcessor -> postProcessProperties, pvs:{}, bean:{}, beanName:{}",
					pvs, bean, beanName);
		}
		return pvs;
	}

	/**
	 * 如果返回null，接下来的BeanPostProcessors都不会执行。
	 */
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		if(bean.getClass() == PersonService.class) {
			logger.info("MyInstantiationAwareBeanPostProcessor -> postProcessBeforeInitialization, bean:{}, beanName:{}",
					bean, beanName);
		}
		return bean;
	}

	/**
	 * 如果返回null,接下来的BeanPostProcessors都不会执行
	 */
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if(bean.getClass() == PersonService.class) {
			logger.info("MyInstantiationAwareBeanPostProcessor -> postProcessAfterInitialization, bean:{}, beanName:{}",
					bean, beanName);
		}
		return bean;
	}
}
