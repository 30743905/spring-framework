package org.simon.transaction;

import org.simon.aop.proxyfactory2.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Administrator
 * @Copyright © 2019 tiger Inc. All rights reserved.
 * @create 2019-02-20 下午 22:53
 * @Description:TODO
 */
public class DBConnection {

	private static String url = "jdbc:mysql://localhost:3306/test001?useUnicode=true&useSSL=true&serverTimezone=GMT%2B8";
	private static String username = "root";
	private static String password = "root";


	public static Connection getConnection(){
		try {
			return DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
