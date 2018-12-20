/**
 * 
 */
package com.zane.scaffold.util.export;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.zane.scaffold.sercvie.export.ExportService;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * @author Zane
 * @date 2018年11月21日
 * @describe
 */
@Component("exportManager")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ExportManager {
	@Resource
	private ExportService exportService;

	// @formatter:off
	/**
	 * Title: uniteArray2List Description:
	 * 将两个数组合并为一个list，第一个数组中的值作为key，第二个数组中对应下标的值作为value，二者组成一个Map对象存放与list中
	 * Created On: 2012-3-20 上午9:48:20
	 * 
	 * @author ChenZhouMian
	 *         <p>
	 * @param str1
	 * @param str2
	 * @return
	 * @return List<Map<String,String>>
	 */
	// @formatter:on
	public static List<Map<String, String>> uniteArray2List(String[] str1, String[] str2) {
		List list = new ArrayList();
		if (str1.length == str2.length) {// 仅当二者长度相同的时候执行以下操作：组装key-value对象，存放于lit中
			for (int i = 0; i < str1.length; i++) {
				Map map = new HashMap();
				map.put(str1[i], str2[i]);
				list.add(map);
			}
		}
		return list;
	}

	// @formatter:off
	/**
	 * Title: toColChoose Description: 根据所传的表名或者视图名称，获取其列名和对应的注释 Created On:
	 * 2012-3-20 上午9:42:33
	 * 
	 * @author ChenZhouMian
	 *         <p>
	 * @param tableName
	 * @param whereSql
	 * @param request
	 * @return void
	 */
	// @formatter:on
	public void toColChoose(String tableName, String whereSql, HttpServletRequest request) {
		String[] columnName = exportService.getColumnName(tableName);// 获取所有字段
		String[] columnCNName = exportService.getColumnComment(columnName, tableName);// 获取所有字段对应的注释
		List tableColumns = uniteArray2List(columnName, columnCNName);
		request.setAttribute("tableColumns", tableColumns);
		request.setAttribute("tableName", tableName);
		request.setAttribute("whereSql", whereSql);
	}

	// @formatter:off
	/**
	 * Title: export Description: 导出数据 Created On: 2012-3-20 下午2:04:10
	 * 
	 * @author ChenZhouMian
	 *         <p>
	 * @param request
	 * @param whereSql
	 * @param tableName
	 * @param chooseCol
	 * @param response
	 * @return void
	 */
	// @formatter:on
	public void export(HttpServletRequest request, String whereSql, String tableName, String chooseCol,
			HttpServletResponse response) {
		String[] chooseColName = chooseCol.split("!!SplitTwo!!");// 选中的列
		String sql = getExpSql(tableName, whereSql, chooseColName);// 组装SQL语句，筛选数据
		String[] excelTit = exportService.getColumnComment(chooseColName, tableName);// 获取所有选取列对应的注释
		List valueList = exportService.getList(sql);// 执行SQL语句，获取数据列表
		writeExcel(excelTit, chooseColName, valueList, response);
	}

	// @formatter:off
	/**
	 * Title: writeExcel Description: 将数据导出到Excel中，此方法主要是对各类参数进行准备 Created On:
	 * 2012-3-20 下午2:04:21
	 * 
	 * @author ChenZhouMian
	 *         <p>
	 * @param excelTit
	 *            - 列注释
	 * @param chooseColName
	 *            - 选中列
	 * @param valueList
	 *            - 要导出的数据列表
	 * @param response
	 * @return void
	 */
	// @formatter:on
	private void writeExcel(String[] excelTit, String[] chooseColName, List valueList, HttpServletResponse response) {
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		try {
			response.setHeader("Content-disposition", "filename=" + new String(("数据导出.xls").getBytes("GBK"), "8859_1"));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		try {
			write2Excel(response.getOutputStream(), excelTit, chooseColName, valueList);
			response.flushBuffer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// @formatter:off
	/**
	 * Title: write2Excel Description: 将数据写入到Excel文件中 Created On: 2012-3-20
	 * 下午2:02:39
	 * 
	 * @author ChenZhouMian
	 *         <p>
	 * @param outputStream
	 * @param excelTit
	 * @param chooseColName
	 * @param valueList
	 * @return void
	 */
	// @formatter:on
	private void write2Excel(ServletOutputStream outputStream, String[] excelTit, String[] chooseColName,
			List valueList) {
		// boolean result = false;
		try {
			WritableWorkbook wwb = Workbook.createWorkbook(outputStream);// 构建Excel对象
			WritableSheet sheet = wwb.createSheet("数据导出", 0);
			for (int i = 0; i < excelTit.length; i++) {// 预留一行空的
				writeACell("", sheet, i, 0);
			}
			for (int i = 0; i < excelTit.length; i++) {// 列名
				writeACell(excelTit[i], sheet, i, 1);
			}

			for (int i = 0; i < valueList.size(); i++) {// 真实数据
				HashMap map = (HashMap) valueList.get(i);
				for (int y = 0; y < chooseColName.length; y++) {
					writeACell((String) map.get(chooseColName[y].toLowerCase()), sheet, y, i + 2);
				}
			}
			wwb.write();
			wwb.close();
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// @formatter:off
	/**
	 * Title: writeACell Description: 创建Excel单元格并写入数据 Created On: 2012-3-20
	 * 下午2:01:30
	 * 
	 * @author ChenZhouMian
	 *         <p>
	 * @param contents
	 * @param sheet
	 * @param col
	 * @param row
	 * @return void
	 */
	// @formatter:on
	public static void writeACell(String contents, WritableSheet sheet, int col, int row) {
		try {
			sheet.addCell(new Label(col, row, contents));
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
	}

	// @formatter:off
	/**
	 * Title: getExpSql Description: 组装数据导出的SQL语句 Created On: 2012-3-20
	 * 下午2:00:23
	 * 
	 * @author ChenZhouMian
	 *         <p>
	 * @param tableName
	 *            - 导出的目标表名
	 * @param whereSql
	 *            - 过滤条件
	 * @param chooseColName
	 *            - 导出列
	 * @return
	 * @return String
	 */
	// @formatter:on
	private String getExpSql(String tableName, String whereSql, String[] chooseColName) {
		StringBuilder sql = new StringBuilder();
		sql.append("select ");
		for (int i = 0; i < chooseColName.length; i++) {
			sql.append(chooseColName[i]);
			if (i < chooseColName.length - 1)
				sql.append(",");
		}
		sql.append(" from ");
		sql.append(tableName.toUpperCase());
		sql.append(" a ");
		if (whereSql != null)
			sql.append(" " + whereSql);
		return sql.toString();
	}
}
