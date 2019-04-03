package org.simon.ioc.demo1;

/**
 * @author Administrator
 * @Copyright © 2019 tiger Inc. All rights reserved.
 * @create 2019-03-15 上午 0:31
 * @Description:TODO
 */
public class UserServiceImpl implements UserService {
	@Override
	public String say(String name) {
		return "hello,"+name;
	}
}
