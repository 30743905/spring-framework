package org.simon.ioc.beandefinition;

/**
 * @author Administrator
 * @Copyright © 2019 tiger Inc. All rights reserved.
 * @create 2019-02-10 下午 20:55
 * @Description:TODO
 */
public class Service2 {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Service2() {
		System.out.println("Service2 构造器执行 "+System.identityHashCode(this));
	}
}
