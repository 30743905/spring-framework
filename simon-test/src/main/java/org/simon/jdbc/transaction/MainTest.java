package org.simon.jdbc.transaction;

import org.junit.Test;
import org.simon.jdbc.transaction.dao.LogDao;
import org.simon.jdbc.transaction.dao.UserDao;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;


/**
 * @author Administrator
 * @Copyright © 2019 tiger Inc. All rights reserved.
 * @create 2019-02-20 下午 22:37
 * @Description:TODO
 */
public class MainTest {

	@Test
	public void transactionTest() {
		try {

			UserDao userDao = new UserDao();
			LogDao logDao = new LogDao();


			userDao.updateUser();
			logDao.saveLog();

			Queue<String> queue = new LinkedList();
			Deque<String> deque = new LinkedList<>();


		}catch (Exception e){
			e.printStackTrace();
		}



	}

}
