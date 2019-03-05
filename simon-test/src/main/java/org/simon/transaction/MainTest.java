package org.simon.transaction;

import org.junit.Test;
import org.simon.transaction.dao.LogDao;
import org.simon.transaction.dao.UserDao;


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
		}catch (Exception e){
			e.printStackTrace();
		}



	}

}
