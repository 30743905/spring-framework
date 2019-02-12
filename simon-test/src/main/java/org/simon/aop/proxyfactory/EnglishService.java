package org.simon.aop.proxyfactory;

/**
 * @author Administrator
 * @Copyright © 2019 tiger Inc. All rights reserved.
 * @create 2019-02-12 下午 22:34
 * @Description:TODO
 */
public class EnglishService implements PeopleService {
	@Override
	public void sayHello() {
		System.err.println("Hi~");
	}

	@Override
	public void printName(String name) {
		System.err.println("Your name:" + name);
	}
}
