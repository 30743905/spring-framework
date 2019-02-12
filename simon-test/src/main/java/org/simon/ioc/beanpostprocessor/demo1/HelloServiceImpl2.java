package org.simon.ioc.beanpostprocessor.demo1;

import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * @Copyright © 2019 tiger Inc. All rights reserved.
 * @create 2019-02-12 下午 22:07
 * @Description:TODO
 */
@Service
public class HelloServiceImpl2 implements HelloService {
	@Override
	public void sayHello() {
		System.out.println("你好我是HelloServiceImpl2");
	}
}