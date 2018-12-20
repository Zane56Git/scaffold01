/**
 * 
 */
package com.zane.scaffold.sercvie.export;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Zane
 * @date 2018年11月21日
 * @describe
 */
public interface ExportService {

	String[] getColumnName(String tableName);// 获取参数指定的表或者视图的所有字段

	String[] getColumnComment(String[] columnName, String tableName);// 获取参数指定的表或者视图的所有字段对应的注释

	List getList(String sql);// 获取参数中的SQL语句对应的实体列表

	void writeExcel(String[] excelTit, String[] chooseColName, List valueList, HttpServletResponse response);// 将参数所指定的数据写入Excel文件中

}
