package org.simon.aop.proxyfactory2;

/**
 * @author Administrator
 * @Copyright © 2019 tiger Inc. All rights reserved.
 * @create 2019-02-14 下午 23:03
 * @Description:TODO
 */
public class LoginServiceImpl implements LoginService {
	public boolean login(User user) {
		System.out.println(user);
		if (user == null) {
			return false;
		} else if (user.getUsername() == "xinchun.wang" && user.getPassword() == "123456") {
			return true;
		}
		return false;
	}

	public boolean test(User user) {
		System.out.println(user);
		if (user == null) {
			return false;
		} else if (user.getUsername() == "xinchun.wang" && user.getPassword() == "123456") {
			return true;
		}
		return false;
	}
}