package com.chenfeng.xiaolyuh.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chenfeng.xiaolyuh.entity.DemoObj;

@Controller
public class AdviceController {
	Logger log = LoggerFactory.getLogger(AdviceController.class);
	
	@RequestMapping("/advice")
	public String getSomething(@ModelAttribute("msg") String msg,DemoObj obj){//1
		log.debug("参数id：" + obj.getId());
		log.debug("参数name：" + obj.getName());
		throw new IllegalArgumentException("非常抱歉，参数有误/"+"来自@ModelAttribute:"+ msg);
	}

}
