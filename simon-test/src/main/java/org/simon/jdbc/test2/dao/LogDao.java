package org.simon.jdbc.test2.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Date;

/**
 * @author Administrator
 * @Copyright © 2019 tiger Inc. All rights reserved.
 * @create 2019-02-20 下午 22:51
 * @Description:TODO
 */
public class LogDao {
	private JdbcTemplate jdbcTemplate;

	public LogDao(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}

	public void saveLog(String detail) {
		int ret = jdbcTemplate.update("insert into log(time, detail) values (?, ?)",
				new Date(System.currentTimeMillis()), detail);
		System.out.printf("影响记录数：%d\r\n", ret);
	}

}
