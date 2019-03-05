package org.simon.aop.demo3;

import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.RegexpMethodPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author Administrator
 * @Copyright © 2019 tiger Inc. All rights reserved.
 * @create 2019-02-18 下午 23:23
 * @Description:TODO
 */
@Configuration
@ComponentScan("org.simon.aop.demo3")
public class TestConfig  {

	/*@Bean
	public RegexpMethodPointcutAdvisor regexpMethodPointcutAdvisor(){
		RegexpMethodPointcutAdvisor regexpMethodPointcutAdvisor = new RegexpMethodPointcutAdvisor();
		regexpMethodPointcutAdvisor.setPatterns(".*greet.*");
		regexpMethodPointcutAdvisor.setAdvice(new BeforeAdvice1());
		return regexpMethodPointcutAdvisor;
	}*/

	@Bean
	public DefaultPointcutAdvisor defaultPointcutAdvisor(){
		AnnotationMatchingPointcut pc =
				AnnotationMatchingPointcut.forClassAnnotation(RequireAdvice.class);
		DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pc, new BeforeAdvice1());
		System.out.println("***advisor");
		return advisor;
	}

	@Bean
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
		DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
		defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
		return defaultAdvisorAutoProxyCreator;
	}
}
