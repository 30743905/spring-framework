package org.simon.aop.transaction;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author 36410
 * @Copyright © 2019 tiger Inc. All rights reserved.
 * @create 2019-03-05 11:23 Description:TODO
 */
public class TransactionDemo {

  private HikariDataSource hikariDataSource;

  @Before
  public void init(){
    HikariConfig hikariConfig = new HikariConfig();
    hikariConfig.setDriverClassName("com.mysql.jdbc.Driver");
    hikariConfig.setJdbcUrl("jdbc:mysql://192.168.236.128:3306/test?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&useSSL=false");
    hikariConfig.setUsername("root");
    hikariConfig.setPassword("123456");
    hikariConfig.setConnectionTimeout(30000);
    hikariConfig.setMaxLifetime(1800000);
    hikariConfig.setMaximumPoolSize(100);
    hikariConfig.setPoolName("ds-simon");
    hikariConfig.setAutoCommit(true);
    hikariConfig.setReadOnly(false);

    hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSize", "250");//指定了local cache的大小，使用了LRU进行逐出
    hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSqlLimit", "2048");//长度限制,默认256,超过该长度后,不使用预编译
    hikariConfig.addDataSourceProperty("dataSource.useServerPrepStmts", "true");

    hikariDataSource = new HikariDataSource(hikariConfig);
  }


  @Test
  public void test2(){
    Connection conn = null;
    try {
      /**
       * DataSourceUtils.getConnection(DataSource dataSource):从指定的数据源中获取和当前线程绑定的Connection
       */
      conn = DataSourceUtils.getConnection(hikariDataSource);
      System.out.println(System.identityHashCode(conn));
      System.out.println(System.identityHashCode(DataSourceUtils.getConnection(hikariDataSource)));
      //默认是没有事务的，需要开启事务
      conn.setAutoCommit(false);
      PreparedStatement statement = conn
          .prepareStatement("insert into user(username, age) values(?, ?)");
      statement.setString(1, "lisi1");
      statement.setInt(2, 10);
      int ret = statement.executeUpdate();
      System.out.println("ret:"+ret);

      int i = 10/0;

      statement = conn
          .prepareStatement("insert into user(username, age) values(?, ?)");
      statement.setString(1, "lisi2");
      statement.setInt(2, 10);

    } catch (SQLException e) {
      try {
        conn.rollback();
      } catch (SQLException e1) {
        e1.printStackTrace();
      }
    }finally {
      DataSourceUtils.releaseConnection(conn, hikariDataSource);
    }
  }

  @Test
  public void test3(){
    JdbcTemplate jdbcTemplate = new JdbcTemplate(hikariDataSource);

    DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(hikariDataSource);

    DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
    definition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
    definition.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);

    //开启事务，得到事务状态
    TransactionStatus txStatus = transactionManager.getTransaction(definition);
    try{
      System.out.println("---------新增用户---------");
      jdbcTemplate.update("insert into user(username, age) values(?, ?)", "test1", 10);
      //int i = 1/0;
      System.out.println("2222222222");
      jdbcTemplate.update("insert into user(username, age) values(?, ?)", "test2", 10);

      transactionManager.commit(txStatus);
    }catch (Exception e){
      System.out.println("保存用户信息异常"+e);
      transactionManager.commit(txStatus);
    }
  }

  @Test
  public void test4(){
    final JdbcTemplate jdbcTemplate = new JdbcTemplate(hikariDataSource);
    int count = jdbcTemplate.queryForObject("select count(*) from user", Integer.class);
    System.out.println("count:"+count);

    /**
     * TransactionManager使用的数据源和JdbcTemplate数据源一致，事务才会生效
     */
    final DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(hikariDataSource);
    TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
    transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_DEFAULT);
    transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

    try{
      System.out.println("---------新增用户---------");
      transactionTemplate.execute(new TransactionCallbackWithoutResult() {
        @Override
        protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
          try{
            jdbcTemplate.update("insert into user(username, age) values(?, ?)", "test7-1", 10);
            int i = 1/0;
            jdbcTemplate.update("insert into user(username, age) values(?, ?)", "test7-2", 10);
          }catch (Exception e){
            //回滚
            transactionStatus.setRollbackOnly();
          }
        }
      });



      //int i = 1/0;
      //System.out.println("2222222222");


/*      transactionTemplate.execute(new TransactionCallbackWithoutResult() {
        @Override
        protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
          jdbcTemplate.update("insert into user(username, age) values(?, ?)", "test2-2", 10);
        }
      });*/

      System.out.println("33333333");
      System.out.println(jdbcTemplate.queryForObject("select count(*) from user", Integer.class));
    }catch (Exception e){
      System.out.println("保存用户信息异常"+e);
    }
  }

  @Test
  public void test5(){

    Logger logger = null;

    final JdbcTemplate jdbcTemplate = new JdbcTemplate(hikariDataSource);
    int count = jdbcTemplate.queryForObject("select count(*) from user", Integer.class);
    System.out.println("count:"+count);

    final DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(hikariDataSource);
    TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
    transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_DEFAULT);
    transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

    service3(transactionTemplate, jdbcTemplate);
    //service2(transactionTemplate, jdbcTemplate);

  }


  private void service3(final TransactionTemplate transactionTemplate, final JdbcTemplate jdbcTemplate){
    transactionTemplate.execute(new TransactionCallbackWithoutResult() {
      @Override
      protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
          jdbcTemplate.update("insert into user(username, age) values(?, ?)", "test41-1", 10);

          transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
              jdbcTemplate.update("insert into user(username, age) values(?, ?)", "test41-2", 10);
              transactionStatus.setRollbackOnly();
            }
          });
      }
    });
  }


  private void service1(final TransactionTemplate transactionTemplate, final JdbcTemplate jdbcTemplate){
    transactionTemplate.execute(new TransactionCallbackWithoutResult() {
      @Override
      protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
          jdbcTemplate.update("insert into user(username, age) values(?, ?)", "test29-1", 10);
          service2(transactionTemplate, jdbcTemplate);
          //transactionStatus.setRollbackOnly();
      }
    });
  }

  private void service2(TransactionTemplate transactionTemplate, final JdbcTemplate jdbcTemplate){
    transactionTemplate.execute(new TransactionCallbackWithoutResult() {
      @Override
      protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
        try{
          jdbcTemplate.update("insert into user(username, age) values(?, ?)", "test29-2", 10);
          int i = 10/0;
        }catch (Exception e){
          //回滚
          transactionStatus.setRollbackOnly();
        }
      }
    });
  }


  @Test
  public void test1(){


    DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(hikariDataSource);

    DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
    definition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
    definition.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);


    TransactionStatus txStatus = transactionManager.getTransaction(definition);
    try{
      System.out.println("---------新增用户---------");





      transactionManager.commit(txStatus);
    }catch (Exception e){
      System.out.println("保存用户信息异常"+e);
      transactionManager.rollback(txStatus);
    }

  }

  private void insertUser(UserDo userDo, DataSourceTransactionManager transactionManager){

  }



}
