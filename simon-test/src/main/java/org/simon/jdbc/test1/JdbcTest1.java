package org.simon.jdbc.test1;

import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * @author Administrator
 * @Copyright © 2019 tiger Inc. All rights reserved.
 * @create 2019-03-16 下午 16:01
 * @Description:TODO
 */
public class JdbcTest1 {
	private static String url = "jdbc:mysql://192.168.236.128:3306/test?useUnicode=true&serverTimezone=GMT%2B8";
	private static String username = "root";
	private static String password = "123456";

	@Test
	public void jdbcTemplateTest(){
		/**
		 * DriverManagerDataSource是Spring配置内置的连接池，在每个连接请求时都新建一个连接，根本没有连接池的作用
		 * DriverManagerDataSource提供的连接没有进行池管理，当连接数到达一定的大小会出现异常。
		 */
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		// 创建模板类
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		/**
		 * 第一条sql会执行并提交，第二条没有执行
		 */
		jdbcTemplate.update("insert into user values (?,?)", "user-010",10);
		int ret = 1/0;
		jdbcTemplate.update("insert into user values (?,?)", "user-011",10);

	}
}
