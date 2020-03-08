package org.simon.ioc.instantiationAwareBeanPostProcessor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author Administrator
 * @Copyright © 2019 tiger Inc. All rights reserved.
 * @create 2019-04-04 下午 23:17
 * @Description:TODO
 */
@Component
public class PersonService {

	private Person person;

	private String name;
	@Value("20")
	private int age;

	public PersonService(){
		System.out.println("PersonService 构造方法执行");
	}

	@PostConstruct
	private void init(){
		System.out.println("PersonService 初始化方法执行");
	}

	public String getName() {
		return name;
	}

	@Value("${person.service.name}")
	public void setName(String name) {
		this.name = name;
	}

	@Autowired
	public void setPerson(Person person) {
		this.person = person;
	}
}
