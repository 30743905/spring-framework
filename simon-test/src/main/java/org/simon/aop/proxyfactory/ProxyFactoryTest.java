package org.simon.aop.proxyfactory;

/**
 * @author Administrator
 * @Copyright © 2019 tiger Inc. All rights reserved.
 * @create 2019-02-12 下午 22:35
 * @Description:TODO
 */
import org.junit.Test;
import org.springframework.aop.framework.ProxyFactory;


public class ProxyFactoryTest {
	@Test
	public void classProxy() {
		//代理对象未指定接口，使用CGLIB生成代理类
		ProxyFactory factory = new ProxyFactory();
		//设置代理对象
		factory.setTarget(new MyTarget());
		//设置增强内容
		factory.addAdvice(new AroundInteceptor());
		MyTarget targetProxy = (MyTarget) factory.getProxy();
		targetProxy.printName();
		System.err.println(targetProxy.getClass().getName());
	}

	@Test
	public void interfaceProxy() {
		//代理对象指定接口PeopleService，目标类为实现PeopleService的EnglishService，使用JDK proxy生成代理类
		ProxyFactory factory = new ProxyFactory();
		//设置代理的接口
		factory.setInterfaces(PeopleService.class );
		//设置增强内容
		factory.addAdvice(new AroundInteceptor());
		//设置代理对象
		factory.setTarget(new EnglishService());
		PeopleService peopleProxy = (PeopleService) factory.getProxy();
		peopleProxy.sayHello();
		System.err.println(peopleProxy.getClass().getName());
	}

	@Test
	public void interfaceProxy2() {
		//代理对象指定接口PeopleService，目标类为实现PeopleService的EnglishService，使用JDK proxy生成代理类
		ProxyFactory factory = new ProxyFactory();
		//设置代理的接口
		factory.setInterfaces(PeopleService.class );
		//设置增强内容
		factory.addAdvice(new TestInteceptor(new EnglishService()));
		PeopleService peopleProxy = (PeopleService) factory.getProxy();
		peopleProxy.sayHello();
		System.err.println(peopleProxy.getClass().getName());
	}
}