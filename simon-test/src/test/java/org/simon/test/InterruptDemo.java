package org.simon.test;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 * @Copyright © 2019 tiger Inc. All rights reserved.
 * @create 2019-02-23 上午 0:13
 * @Description:TODO
 */

public class InterruptDemo {
	private static int i;

	public static void main(String[] args) throws
			InterruptedException {
		Thread thread = new Thread(() -> {
			while (!Thread.currentThread().isInterrupted()) {
				i++;
			}
			System.out.println("Num:" + i);
		}, "interruptDemo");
		thread.start();
		TimeUnit.SECONDS.sleep(1);
		thread.interrupt();
		System.out.println(thread.isInterrupted());
		System.out.println(thread.isInterrupted());
	}

	@Test
	public void test2(){
		Thread thread=new Thread(()->{
			while(true){
				boolean ii=Thread.currentThread().isInterrupted();
				if(ii){
					System.out.println("before:"+ii);
					Thread.interrupted();//对线程进行复位，中断标识为false
					System.out.println("after:"+Thread.currentThread()
							.isInterrupted());
				}
			}
		});
		thread.start();
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		thread.interrupt();//设置中断标识,中断标识为 true
	}

	@Test
	public void test3() throws InterruptedException {
		Thread thread=new Thread(()->{
			while(true){
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					System.out.println("thread:"+Thread.currentThread().isInterrupted());
					//抛出该异常，会将复位标识设置为 false
					e.printStackTrace();
				}
			}
		});
		thread.start();
		TimeUnit.SECONDS.sleep(1);
		thread.interrupt();//设置复位标识为 true
		//TimeUnit.SECONDS.sleep(1);
		System.out.println("******>"+thread.isInterrupted());//false
	}
}
