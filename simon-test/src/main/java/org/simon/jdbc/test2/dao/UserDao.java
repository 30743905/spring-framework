package org.simon.jdbc.test2.dao;

import org.springframework.jdbc.core.JdbcTemplate;


/**
 * @author Administrator
 * @Copyright © 2019 tiger Inc. All rights reserved.
 * @create 2019-02-20 下午 22:51
 * @Description:TODO
 */
public class UserDao {
	private JdbcTemplate jdbcTemplate;

	public UserDao(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}
	public void saveUser(String name, int age) {
		int ret = jdbcTemplate.update("insert into user(name, age) values (?, ?)", name, age);
		System.out.printf("影响记录数：%d\r\n", ret);
	}

}
