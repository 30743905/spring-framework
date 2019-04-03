package org.simon.aop.demo3;


import org.springframework.stereotype.Component;

/**
 * @author Administrator
 * @Copyright © 2019 tiger Inc. All rights reserved.
 * @create 2019-02-14 下午 23:03
 * @Description:TODO
 */
@RequireAdvice
@Component
public class LoginServiceImpl implements LoginService {
	public LoginServiceImpl(){
		System.out.println("LoginServiceImpl构造器");
	}


	@Override
	public boolean greetTo(String name) {
		System.out.println("greetTo:"+name);
		return false;
	}

	public boolean serverTo(String name) {
		System.out.println("serverTo:"+name);
		return false;
	}

	@Override
	public void test(String k, int age, Object ccc1) {

	}


}