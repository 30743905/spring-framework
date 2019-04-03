package org.simon.jdbc.test2;

import org.simon.jdbc.test2.dao.LogDao;
import org.simon.jdbc.test2.dao.UserDao;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author Administrator
 * @Copyright © 2019 tiger Inc. All rights reserved.
 * @create 2019-03-16 下午 23:53
 * @Description:TODO
 */
public class BizService1 {
	private UserDao userDao;
	private LogDao logDao;
	private TransactionTemplate transactionTemplate;

	public BizService1(UserDao userDao, LogDao logDao, TransactionTemplate transactionTemplate){
		this.userDao = userDao;
		this.logDao = logDao;
		this.transactionTemplate = transactionTemplate;
	}

	public void testService() {
		transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		transactionTemplate.execute(new TransactionCallback<Integer>() {
			@Override
			public Integer doInTransaction(TransactionStatus status) {
				// 扣钱
				userDao.saveUser("user-100", 10);
				//int a = 10 / 0;
				// 加钱
				logDao.saveLog("user-100-log");
				return 1;
			}
		});
	}
}
