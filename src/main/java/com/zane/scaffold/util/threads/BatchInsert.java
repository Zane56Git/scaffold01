/**
 * 
 */
package com.zane.scaffold.util.threads;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Zane
 * @date 2019年2月19日
 * @describe (待修改)
 */
public class BatchInsert {

	public void BatchInsert(Connection conn) {
		try {
			conn.setAutoCommit(false);
			Long beginTime = System.currentTimeMillis();
			// 构造预处理statement
			PreparedStatement pst = conn.prepareStatement("insert into t1(id) values (?)");
			// 1万次循环
			for (int i = 1; i <= 100000; i++) {
				pst.setInt(1, i);
				pst.addBatch();
				// 每1000次提交一次
				if (i % 1000 == 0) {// 可以设置不同的大小；如50，100，500，1000等等
					pst.executeBatch();
					conn.commit();
					pst.clearBatch();
				}
			}
			Long endTime = System.currentTimeMillis();
			System.out.println("pst+batch：" + (endTime - beginTime) / 1000 + "秒");
			pst.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}