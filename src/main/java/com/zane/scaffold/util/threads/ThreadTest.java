/**
 * 
 */
package com.zane.scaffold.util.threads;

/**
 * @author Zane
 * @date 2019年2月15日
 * @describe
 */
// 先看一个多线程模拟卖票的例子，总票数7张，两个线程同时卖票：
// public class ThreadTest {
// public static void main(String[] args) {
// Runnable r = new MyThread();
// Thread t1 = new Thread(r);
// Thread t2 = new Thread(r);
// t1.start();
// t2.start();
// }
// }
//
// class MyThread implements Runnable {
// private int tickets = 7; // 票数
//
// @Override
// public void run() {
// while (tickets > 0) {
// System.out.println("tickets:" + tickets);
// tickets--;
// try {
// Thread.sleep(1000);
// } catch (InterruptedException e) {
// e.printStackTrace();
// }
// }
// }
// }

/*
 * 运行结果不符合我们的预期，因为两个线程使用共享变量tickets，存在着由于交叉操作而破坏数据的可能性，
 * 
 * 这种潜在的干扰被称作临界区，通过同步对临界区的访问可以避免这种干扰。
 * 
 * 在Java语言中，每个对象都有与之关联的同步锁，并且可以通过使用synchronized方法或语句来获取或释放
 * 
 * 这个锁。在多线程协作时，如果涉及到对共享对象的访问，在访问对象之前，线程必须获取到该对象的同步
 * 
 * 锁，获取到同步锁后可以阻止其他线程获得这个锁，直到持有锁的线程释放掉锁为止。
 */
public class ThreadTest {
	public static void main(String[] args) {
		Runnable r = new MyThread();
		Thread t1 = new Thread(r);
		Thread t2 = new Thread(r);
		t1.start();
		t2.start();
	}
}

class MyThread implements Runnable {
	private int tickets = 7;

	@Override
	public void run() {
		while (tickets > 0) {
			synchronized (this) { // 获取当前对象的同步锁
				if (tickets > 0) {
					System.out.println("tickets:" + tickets);
					tickets--;
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
		System.out.println("余票不足，当前余票:" + tickets);
	}
}
