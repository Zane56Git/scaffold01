/**
 * 
 */
package com.zane.scaffold.util.threads;

/**
 * @author Zane
 * @date 2019年2月19日
 * @describe 多线程
 */
public class Test {

	public static void main(String[] args) {
		// 开启10个线程插入10万条数据
		for (int i = 1; i <= 10; i++) {
			new MyThreadInsert().start();
		}

	}

}
