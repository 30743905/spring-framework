package org.simon.mybatis;

/**
 * 你搞忘写注释了
 *
 * @author Admin
 * @Copyright © 2019 tiger Inc. All rights reserved.
 * @create 2019-09-17 23:59
 */
@Mapper("userMapper")
public interface UserMapper {
	@Sql("select * from user where id = ?")
	void get(int id);
	@Sql("insert into user(username, age) values(?,?)")
	void insert(String username, int age);
}
