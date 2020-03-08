package org.simon.other.anno.demo1;

/**
 * 你搞忘写注释了
 *
 * @author Admin
 * @Copyright © 2019 tiger Inc. All rights reserved.
 * @create 2019-09-18 21:14
 */
@BTable
public class Sub implements Super{
	private int subx;
	public int suby;
	private Sub()
	{
	}
	public Sub(int i){
	}
	private int subX(){
		return 0;
	}
	public int subY(){
		return 0;
	}

	@Override
	public void funa(int a, int b) {
		System.out.println("time:"+System.currentTimeMillis()+",sum:"+(a+b));
	}
}
