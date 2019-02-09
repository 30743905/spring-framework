package org.simon.test1;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value = "abcService")
@Scope("prototype")
public class TestService {
	
	private String name;
	private int age;
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
