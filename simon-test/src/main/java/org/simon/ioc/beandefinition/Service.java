package org.simon.ioc.beandefinition;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Administrator
 * @Copyright © 2019 tiger Inc. All rights reserved.
 * @create 2019-02-10 下午 16:49
 * @Description:TODO
 */
public class Service {
	private String name;
	private Integer age;

	public Service() {
		System.out.println("Service 构造器执行 "+System.identityHashCode(this));
	}

	private Service2 service2;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Service2 getService2() {
		return service2;
	}

	public void setService2(Service2 service2) {
		this.service2 = service2;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
}
