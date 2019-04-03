package org.simon.aop.transaction;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author 36410
 * @Copyright © 2019 tiger Inc. All rights reserved.
 * @create 2019-03-05 17:29 Description:TODO
 */
public class UserDao {

  public int insertUser(String username, int age, JdbcTemplate template){
    return template.update("insert into user(username, age) values(?, ?)", username, age);
  }

}
