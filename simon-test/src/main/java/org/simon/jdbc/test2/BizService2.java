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
public class BizService2 {
	private UserDao userDao;
	private LogDao logDao;
	private TransactionTemplate transactionTemplate;

	public BizService2(UserDao userDao, LogDao logDao, TransactionTemplate transactionTemplate){
		this.userDao = userDao;
		this.logDao = logDao;
		this.transactionTemplate = transactionTemplate;
	}

	public void testService() {
		System.out.println("*******************");
		transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		transactionTemplate.execute(new TransactionCallback<Integer>() {
			@Override
			public Integer doInTransaction(TransactionStatus status) {
				try {
					// 扣钱
					userDao.saveUser("user-200", 10);
					int a = 10 / 0;
					// 加钱
					logDao.saveLog("user-200-log");
				}catch (Exception e){
					status.setRollbackOnly();
				}
				return 1;
			}
		});
	}
}
