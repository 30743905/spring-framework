package org.simon.ioc.beandifinitionreader;

import org.springframework.stereotype.Component;

/**
 * @author Administrator
 * @Copyright © 2019 tiger Inc. All rights reserved.
 * @create 2019-03-05 下午 22:19
 * @Description:TODO
 */
@Component
public class TestService2 {
	public void say(){
		System.out.println("time:"+System.currentTimeMillis());
	}
}
