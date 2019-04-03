package org.simon.jdbc.test2;

import org.junit.Before;
import org.junit.Test;
import org.simon.jdbc.test2.dao.LogDao;
import org.simon.jdbc.test2.dao.UserDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author Administrator
 * @Copyright © 2019 tiger Inc. All rights reserved.
 * @create 2019-03-16 下午 23:43
 * @Description:TODO
 */
public class TransactionTest {

	private static String url = "jdbc:mysql://192.168.236.128:3306/test?useUnicode=true&serverTimezone=GMT%2B8&useSSL=false";
	private static String username = "root";
	private static String password = "123456";

	private DriverManagerDataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	@Before
	public void init(){
		/**
		 * DriverManagerDataSource是Spring配置内置的连接池，在每个连接请求时都新建一个连接，根本没有连接池的作用
		 * DriverManagerDataSource提供的连接没有进行池管理，当连接数到达一定的大小会出现异常。
		 */
		dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);

		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Test
	public void test1(){
		//创建一个事务管理器
		PlatformTransactionManager transactionManager =
				new DataSourceTransactionManager(dataSource);

		//创建事务管理模板
		TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
		transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

		UserDao userDao = new UserDao(jdbcTemplate);
		LogDao logDao = new LogDao(jdbcTemplate);
		BizService1 bizService1 = new BizService1(userDao, logDao, transactionTemplate);
		BizService2 bizService2 = new BizService2(userDao, logDao, transactionTemplate);
		bizService1.testService();
		bizService2.testService();
	}

}
