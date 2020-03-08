package org.simon.beanfactoryPostProcessor.demo1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * @author Admin
 * @Copyright © 2019 tiger Inc. All rights reserved.
 * @create 2019-08-22 23:58
 * @Description:TODO
 */
@Component
public class Service5 {

	private String name;
	private Integer age;

	@Autowired
	private Service1 service1;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Service1 getService1() {
		return service1;
	}

	public void setService1(Service1 service1) {
		this.service1 = service1;
	}
}
