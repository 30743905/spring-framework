package org.simon.jdbc.transaction;

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

	private static String url = "jdbc:mysql://192.168.236.128:3306/test?useUnicode=true&useSSL=true&serverTimezone=GMT%2B8";
	private static String username = "root";
	private static String password = "123456";


	public static Connection getConnection(){
		try {
			return DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
