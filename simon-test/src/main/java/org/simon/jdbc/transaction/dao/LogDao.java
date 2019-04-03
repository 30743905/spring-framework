package org.simon.jdbc.transaction.dao;

import org.simon.jdbc.transaction.DBConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Administrator
 * @Copyright © 2019 tiger Inc. All rights reserved.
 * @create 2019-02-20 下午 22:51
 * @Description:TODO
 */
public class LogDao {

	/**
	 * setAutoCommit总的来说就是保持数据的完整性，一个系统的更新操作可能要涉及多张表，需多个SQL语句进行操作
	 * 循环里连续的进行插入操作，如果你在开始时设置了：conn.setAutoCommit(false);
	 * 最后才进行conn.commit(),这样你即使插入的时候报错，修改的内容也不会提交到数据库，
	 * 而如果你没有手动的进行setAutoCommit(false);  出错时就会造成，前几条插入，后几条没有，会形成脏数据~~
	 *
	 * setAutoCommit(false)的误用
	 * （设定setAutoCommit(false)没有在catch中进行Connection的rollBack操作，操作的表就会被锁住，造成数据库死锁）：
	 * 误用Connection.setAutoCommit导致的数据库死锁问题。
	 * @throws SQLException
	 */
	public void saveLog() throws SQLException {
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement statment = conn.prepareStatement("insert into tab_log(time, detail) values (?, ?)");
			statment.setDate(1, new Date(System.currentTimeMillis()));
			statment.setString(2, "用户插入成功");
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
