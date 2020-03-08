package org.simon.ioc.instantiationAwareBeanPostProcessor;

import org.springframework.stereotype.Component;

/**
 * @author Administrator
 * @Copyright © 2019 tiger Inc. All rights reserved.
 * @create 2019-04-04 下午 23:17
 * @Description:TODO
 */
@Component
public class Person {
	private String name;
	private int age;

	public Person(){
		System.out.println("Person构造方法执行");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
