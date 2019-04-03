package org.simon.aop.transaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author 36410
 * @Copyright © 2019 tiger Inc. All rights reserved.
 * @create 2019-03-05 17:29 Description:TODO
 */
public class UserService {
  private Logger logger = LoggerFactory.getLogger(UserService.class);

  private UserDao userDao = new UserDao();

  public int insertUser1(final String usename, final int age, TransactionTemplate transactionTemplate, final JdbcTemplate jdbcTemplate){
    //transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
    int re = transactionTemplate.execute(new TransactionCallback<Integer>() {
      public Integer doInTransaction(TransactionStatus transactionStatus) {
        int i = userDao.insertUser(usename+"-2", age, jdbcTemplate);
        transactionStatus.setRollbackOnly();//主动回滚
        return i;
      }
    });
    return re;
  }

  public int insertUser(final String usename, final int age, final TransactionTemplate transactionTemplate, final JdbcTemplate jdbcTemplate){
   // transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
    transactionTemplate.execute(new TransactionCallbackWithoutResult() {
      @Override
      protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
        int i = userDao.insertUser(usename, age, jdbcTemplate);
        int j = insertUser1(usename, age, transactionTemplate, jdbcTemplate);
        //transactionStatus.setRollbackOnly();//主动回滚
        System.out.println("i:"+i);
        System.out.println("j:"+j);
      }
    });
    return 0;
  }


}
