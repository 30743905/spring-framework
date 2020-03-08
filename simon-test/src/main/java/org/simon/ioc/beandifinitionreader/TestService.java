package org.simon.ioc.beandifinitionreader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 * @Copyright © 2019 tiger Inc. All rights reserved.
 * @create 2019-03-05 下午 22:19
 * @Description:TODO
 */
@MyComponent
//@Component
public class TestService {
	@Autowired
	private TestService2 testService2;
	public void say(){
		System.out.println("time:"+System.currentTimeMillis());
	}

	public TestService2 getTestService2() {
		return testService2;
	}
}
