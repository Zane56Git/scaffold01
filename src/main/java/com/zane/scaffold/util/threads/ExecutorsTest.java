/**
 * 
 */
package com.zane.scaffold.util.threads;

import java.util.Random;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Zane
 * @date 2019年2月19日
 * @describe
 */

/**
 * 单服务器用线程池实现秒杀的思路一
 * 
 * @author zhanghaijun
 * 
 */
public class ExecutorsTest {

	public static boolean flag = true; // 秒杀物品的标记

	public static void main(String[] args) {
		ThreadPoolExecutor pool = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,
				new SynchronousQueue<Runnable>());
		SecKillTest t1 = new SecKillTest("张三");
		SecKillTest t2 = new SecKillTest("李四");
		SecKillTest t3 = new SecKillTest("王五");
		// 产生一个1-3之间的随机数：
		int x = 1 + (int) (Math.random() * 3);
		/*
		 * 或者： 产生一个0到x-1的正数，如果想产生浮点数有Random类的nextFloat方法，总之nextXXX方法是用来产生随机数的。
		 * 也可以使用Math类的静态方法random（）方法，返回带正号的 double 值，该值大于等于 0.0 且小于 1.0。
		 */
		Random random = new Random();
		System.out.println("产生一个0到x-1的正数" + random.nextInt(100));

		try {
			pool.execute(t1);
		} catch (Exception e) {
			System.out.println(t1.getUserName() + "没有抢到");
		}

		try {
			pool.execute(t3);
		} catch (Exception e) {
			System.out.println(t3.getUserName() + "没有抢到");
		}

		try {
			pool.execute(t2);
		} catch (Exception e) {
			System.out.println(t2.getUserName() + "没有抢到");
		}
		pool.shutdown();
	}

}

class SecKillTest extends Thread {

	private String userName;

	public SecKillTest(String userName) {
		super();
		this.userName = userName;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(200);
			if (ExecutorsTest.flag) {
				System.out.println(this.userName + "秒杀成功");
				ExecutorsTest.flag = false;
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}