package com.zane.scaffold.controller.mp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.zane.scaffold.entity.mp.Mp;
import com.zane.scaffold.sercvie.MPService;
import com.zane.scaffold.util.common.JsonResult;

@RestController
@RequestMapping("/mp")
// @Api("业务域代码集API")
public class MpController {
	@Autowired
	private MPService mpService;

	@RequestMapping("/query")
	public String index() {
		EntityWrapper<Mp> ew = new EntityWrapper<Mp>();

		List<Mp> list = mpService.selectList(ew);
		System.out.println("MP" + list);
		return "Hello Zane! This is  Spring Boot 2.0!";
	}

	// 返回json
	@GetMapping("/queryJson")
	public JsonResult<?> query(String id, String name) {
		try {
			EntityWrapper<Mp> ew = new EntityWrapper<Mp>();

			List<Mp> list = mpService.selectList(ew);
			return JsonResult.buildSuccessResult(list);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.buildSuccessResult("查询异常，请联系管理员！");
		}
	}
}
