/**
 * 
 */
package com.zane.scaffold.sercvie.export.impl;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zane.scaffold.mapper.export.ExportMapper;
import com.zane.scaffold.sercvie.export.ExportService;

/**
 * @author Zane
 * @date 2018年11月21日
 * @describe
 */
@Service
public class ExportServiceImpl implements ExportService {

	@Autowired
	private ExportMapper exportMapper;

	@Override
	public String[] getColumnName(final String tableName) {
		String[] arr = exportMapper.getColumnName(tableName);
		return arr;
	}

	@Override
	public String[] getColumnComment(final String[] columnName, final String tableName) {
		String[] arr = exportMapper.getColumnComment(columnName, tableName);
		return arr;
	}

	@Override
	public List getList(final String sql) {
		List list = exportMapper.getList(sql);
		return list;
	}

	@Override
	public void writeExcel(String[] excelTit, String[] chooseColName, List valueList, HttpServletResponse response) {

	}

}
