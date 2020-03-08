package org.simon.aop.enhancer;

/**
 * 你搞忘写注释了
 *
 * @author Admin
 * @Copyright © 2019 tiger Inc. All rights reserved.
 * @create 2019-09-21 16:54
 */
public class TestService1 {

	public String hello(String name){
		return "hello,"+name;
	}

	public Integer sum(int a, int b){
		return a+b;
	}
}
