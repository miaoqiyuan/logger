package com.tstkj.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tstkj.demo.UserInfo;
import com.tstkj.demo.service.TestService;
import com.tstkj.logger.anno.TstLogger;
import com.tstkj.logger.service.LogService;

@RestController
@RequestMapping("/test")
@TstLogger("我擦就是测试")
public class Controller implements LogService{

	@Autowired
	private TestService service;
	
	@GetMapping("/t")
	public String testE(String id) {
		try {
			service.t();
			return "ok";
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.getMessage();
		}
	
	}

	@PostMapping("/t1")
	@TstLogger("创建用户")
	public String  create123( UserInfo info) {
		try {
			service.test(info.getUserId());
			return "ok";
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	
	@GetMapping("/t2/{id}")
	@TstLogger("删除用户")
	public String  removeUser(@PathVariable("id") String id) {
		id.split("");
		return "aaaa";
	}
	/**
	 * 用于获取当前登录ID
	 */
	@Override
	public String getInfoById(String id) {
		return null;
	}

}
