package org.simon.jdbc.transaction.dao;

import org.simon.jdbc.transaction.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Administrator
 * @Copyright © 2019 tiger Inc. All rights reserved.
 * @create 2019-02-20 下午 22:51
 * @Description:TODO
 */
public class UserDao {

	public void updateUser() throws SQLException {
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement statment = conn.prepareStatement("insert into tab_user(name, age) values ('product', 10)");
			int ret = statment.executeUpdate();
			conn.commit();
			System.out.printf("影响记录数：%d\r\n", ret);
		} catch (SQLException e) {
			conn.rollback();
			throw e;
		}finally {
			if(conn != null){
				conn.close();
			}
		}
	}

}
