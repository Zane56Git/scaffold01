/**
 * 
 */
package com.zane.scaffold.util.threads;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Zane
 * @date 2019年2月19日
 * @describe
 */

public class TestMultiThreads {

	private static Integer num = 0;

	// Test Shutdown
	public static void executor1() throws Exception {
		ExecutorService pool = Executors.newFixedThreadPool(100);

		CompletionService completionService = new ExecutorCompletionService<Integer>(pool);

		for (int i = 0; i < 100; i++) {
			completionService.submit(new Callable<Integer>() {

				@Override
				public Integer call() throws Exception {
					synchronized (num) {
						return ++num;
					}

				}

			});
		}
		// pool.shutdown();//启动一次顺序关闭，执行以前提交的任务，但不接受新任务。
		for (int i = 0; i < 100; i++) {

			Future<Integer> future = completionService.take();
			System.out.println(future.get());

		}

		System.out.println("==================================");

		if (pool.isShutdown()) {
			System.out.println("pool is shutdown!");
		}

		for (int i = 0; i < 100; i++) {
			completionService.submit(new Callable<Integer>() {

				@Override
				public Integer call() throws Exception {
					synchronized (num) {
						return ++num;
					}

				}

			});
		}
		for (int i = 0; i < 100; i++) {

			Future<Integer> future = completionService.take();
			System.out.println(future.get());

		}
		// 启动一次顺序关闭，执行以前提交的任务，但不接受新任务。
		pool.shutdown();// 必须要有shutdown否则出错
	}

	// Test ShutdownNow
	public static void executor2() throws InterruptedException, ExecutionException {

		ExecutorService pool = Executors.newFixedThreadPool(100);
		CompletionService completionService = new ExecutorCompletionService<Integer>(pool);

		for (int i = 0; i < 100; i++) {

			completionService.submit(new Callable<Integer>() {

				@Override
				public Integer call() throws Exception {
					// 不一定线程安全
					return ++num;
				}
			});

		}

		List<Runnable> restRunList = null;
		for (int i = 0; i < 100; i++) {

			if (i > 50) {
				restRunList = pool.shutdownNow();// 不成功

			}
			System.out.println(completionService.take().get());
		}

		System.out.println("===============================");
		// restRunList为null
		if (restRunList == null) {
			return;
		}
		for (Runnable r : restRunList) {
			System.out.println(r);
			r.run();
		}

	}

	public static void main(String[] args) throws Exception {

		executor1();
		executor2();

	}

}