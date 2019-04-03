package org.simon.aop.transaction;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
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
public class TransactionDemo2 {

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
  public void test4(){


    final JdbcTemplate jdbcTemplate = new JdbcTemplate(hikariDataSource);
    int count = jdbcTemplate.queryForObject("select count(*) from user", Integer.class);
    //int count = jdbcTemplate.queryForInt("select count(*) from user");
    System.out.println("count:"+count);

    final DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(hikariDataSource);
    TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
    transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_DEFAULT);
    transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);

    UserService userService = new UserService();

    try{
      System.out.println("---------新增用户---------");
      userService.insertUser("zhangsan15", 20, transactionTemplate, jdbcTemplate);




    }catch (Exception e){
      System.out.println("保存用户信息异常"+e);
    }
  }

  @Test
  public void test5(){
    /**
     * 使用TransactionAwareDataSourceProxy对数据源进行代理，使数据源具有事务上下文感知能力
     */
    TransactionAwareDataSourceProxy dataSourceProxy = new TransactionAwareDataSourceProxy(
        hikariDataSource);
    Connection conn = null;
    try {
      conn = dataSourceProxy.getConnection();
      System.out.println(conn.getClass().getName());
      System.out.println(System.identityHashCode(conn));
      System.out.println(System.identityHashCode(dataSourceProxy.getConnection()));
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  @Test
  public void test6(){
    DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
    transactionDefinition.setIsolationLevel(TransactionDefinition.ISOLATION_DEFAULT);
    transactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

    DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(hikariDataSource);
    TransactionStatus status = transactionManager.getTransaction(transactionDefinition);
    Connection conn = DataSourceUtils.getConnection(hikariDataSource);
    System.out.println(System.identityHashCode(DataSourceUtils.getConnection(hikariDataSource)));
    System.out.println(System.identityHashCode(DataSourceUtils.getConnection(hikariDataSource)));
    try {
      PreparedStatement statment = conn.prepareStatement("insert into user(username, age) values(?, ?)");
      statment.setString(1, "zhangsan100");
      statment.setInt(2, 100);
      statment.executeUpdate();
      System.out.println("1111111");
      transactionManager.rollback(status);
      System.out.println("2222222222222");
    } catch (SQLException e) {
      e.printStackTrace();
    }
    //template.update("insert into user(username, age) values(?, ?)", username, age);


  }

  @Test
  public void test7(){
    DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
    transactionDefinition.setIsolationLevel(TransactionDefinition.ISOLATION_DEFAULT);
    transactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

    DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(hikariDataSource);
    TransactionStatus status1 = transactionManager.getTransaction(transactionDefinition);
    System.out.println(System.identityHashCode(DataSourceUtils.getConnection(hikariDataSource)));
    TransactionStatus status2 = transactionManager.getTransaction(transactionDefinition);
    System.out.println(System.identityHashCode(DataSourceUtils.getConnection(hikariDataSource)));

    System.out.println(System.identityHashCode(status1));
    System.out.println(System.identityHashCode(status2));
  }

  @Test
  public void test8(){
    DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
    transactionDefinition.setIsolationLevel(TransactionDefinition.ISOLATION_DEFAULT);
    transactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

    DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(hikariDataSource);

    s1(transactionManager, transactionDefinition);
    s2(transactionManager, transactionDefinition);
  }

  private void s1(DataSourceTransactionManager transactionManager, DefaultTransactionDefinition transactionDefinition){
    TransactionStatus status = transactionManager.getTransaction(transactionDefinition);
    Connection conn = DataSourceUtils.getConnection(hikariDataSource);
    System.out.println(System.identityHashCode(conn));
    try {
      PreparedStatement statment = conn.prepareStatement("insert into user(username, age) values(?, ?)");
      statment.setString(1, "zhangsan101");
      statment.setInt(2, 100);
      statment.executeUpdate();
      transactionManager.commit(status);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private void s2(DataSourceTransactionManager transactionManager, DefaultTransactionDefinition transactionDefinition){
    TransactionStatus status = transactionManager.getTransaction(transactionDefinition);
    Connection conn = DataSourceUtils.getConnection(hikariDataSource);
    System.out.println(System.identityHashCode(conn));
    try {
      PreparedStatement statment = conn.prepareStatement("insert into user(username, age) values(?, ?)");
      statment.setString(1, "zhangsan102");
      statment.setInt(2, 100);
      statment.executeUpdate();
      transactionManager.rollback(status);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

}
