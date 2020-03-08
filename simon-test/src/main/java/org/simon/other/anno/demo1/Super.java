package org.simon.other.anno.demo1;

import org.simon.mybatis.Sql;

/**
 * 你搞忘写注释了
 *
 * @author Admin
 * @Copyright © 2019 tiger Inc. All rights reserved.
 * @create 2019-09-18 21:13
 */
@ATable
public interface Super {

	@Sql
	void funa(int a, int b);
	/*private int superx;
	public int supery;
	public Super() {
	}
	private int superX(){
		return 0;
	}
	public int superY(){
		return 0;
	}*/
}