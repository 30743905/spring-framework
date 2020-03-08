package org.simon.ioc.demo2;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author Admin
 * @Copyright © 2019 tiger Inc. All rights reserved.
 * @create 2019-10-21 21:48
 */
@Component
public class MyServiceFactory implements FactoryBean<MyService>, InitializingBean {
	@Override
	public MyService getObject() throws Exception {
		System.out.println("==========getObject");
		return new MyService();
	}

	@Override
	public Class<?> getObjectType() {
		System.out.println("==========getObjectType");
		return MyService.class;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("==========afterPropertiesSet");
	}
}