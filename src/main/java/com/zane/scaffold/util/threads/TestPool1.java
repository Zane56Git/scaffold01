/**
 * 
 */
package com.zane.scaffold.util.threads;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Zane
 * @date 2019年2月18日
 * @describe
 */

// 线程池的使用
public class TestPool1 {
	public static void main(String[] args) {
		// 其中的corePoolSize=10,maximumPoolSize=13.;
		// 当线程的数目到达10个的时候,下一个线程就会进入到缓存队列中
		// 此缓存队列中默认有5个线程,当缓存队列中的线程超过默认值的时候就会重新创建线程,
		// 并且线程数目最多为13+默认缓存队列的值''
		ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 13, 200, TimeUnit.MILLISECONDS,
				new ArrayBlockingQueue<Runnable>(5));
		for (int i = 0; i < 18; i++) {
			MyTask myTask = new MyTask(i);
			executor.execute(myTask);// 提交线程
			System.out.println("线程池中的线程数目:" + executor.getPoolSize() + "队列中等待执行的任务数目:" + executor.getQueue().size()
					+ "已经执行完的任务数目:" + executor.getCompletedTaskCount());
		}
		executor.shutdown();// 启动有序关闭,
	}
}

class MyTask implements Runnable {
	private int taskName;

	public MyTask(int num) {
		this.taskName = num;
	}

	public void run() {
		System.out.println("正在执行:" + taskName);
		try {
			Thread.currentThread().sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("task" + taskName + "执行完毕");
	}

}