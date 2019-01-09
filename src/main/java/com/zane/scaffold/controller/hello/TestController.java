package com.zane.scaffold.controller.hello;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zane.scaffold.sercvie.export.ExportService;
import com.zane.scaffold.util.export.ExportManager;

@RestController
public class TestController {
	@Autowired
	private ExportService exportService;

	ExportManager e = new ExportManager();

	@RequestMapping("/test")
	public String index() {
		return "Hello Zane! This is  Spring Boot 2.0!";
	}

	@RequestMapping("/test/export")
	public void exportSykcxxb(HttpServletResponse response) {
		HttpServletRequest request = null;
		String tableName = "tbl_employee";
		String whereSql = " where id='1'";
		// sonar_Test,jenkins_Test
		// String chooseCol = "KCBH!!SplitTwo!!KCMC!!SplitTwo!!YYXDM" +
		// "!!SplitTwo!!SYXS!!SplitTwo!!XS!!SplitTwo!!ND";
		String chooseCol = "id!!SplitTwo!!last_name!!SplitTwo!!email!!SplitTwo!!gender!!SplitTwo!!age!!SplitTwo!!delete_flag";
		e.export(request, whereSql, tableName, chooseCol, response);
	}
}
