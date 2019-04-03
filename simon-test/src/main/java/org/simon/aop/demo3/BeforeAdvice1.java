package org.simon.aop.demo3;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author Administrator
 * @Copyright © 2019 tiger Inc. All rights reserved.
 * @create 2019-02-14 下午 23:06
 * @Description:TODO
 */

public class BeforeAdvice1 implements MethodBeforeAdvice {
	public void before(Method method, Object[] args, Object target)
			throws Throwable {

		LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
		String[] params = u.getParameterNames(method);
		System.out.println("params:"+Arrays.toString(params));



		System.out.println("BeforeAdvice1.before() execute ");

	}
}

