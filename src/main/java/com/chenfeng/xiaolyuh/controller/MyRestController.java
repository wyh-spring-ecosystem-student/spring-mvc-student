package com.chenfeng.xiaolyuh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chenfeng.xiaolyuh.service.DemoService;

@RestController
public class MyRestController {
	
	@Autowired
	DemoService demoService;
	
	@RequestMapping(value = "/testRest" ,produces="text/plain;charset=UTF-8")
//	@ResponseBody
	public String testRest(){
		return demoService.saySomething();
	}

}
