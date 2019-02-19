/**
 * 
 */
package com.zane.scaffold.util.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Zane
 * @date 2019年2月19日
 * @describe 常用的几种线程池；参考：https://www.cnblogs.com/aaron911/p/6213808.html
 */

public class ThreadPoolExecutorTest {
	public static void main(String[] args) {
		ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
		for (int i = 0; i < 10; i++) {
			final int index = i;
			try {
				Thread.sleep(index * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			cachedThreadPool.execute(new Runnable() {
				public void run() {
					System.out.println(index);
				}
			});
		}
	}
}