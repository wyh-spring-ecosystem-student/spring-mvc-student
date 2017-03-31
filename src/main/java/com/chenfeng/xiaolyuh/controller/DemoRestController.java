package com.chenfeng.xiaolyuh.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chenfeng.xiaolyuh.entity.DemoObj;


@RestController // 声明是一个控制器，并且返回时不需要@ResponseBody
@RequestMapping("/rest")
public class DemoRestController {
	
	@RequestMapping(value = "/getjson", produces={"application/json;charset=UTF-8"}) 
	public DemoObj getjson (DemoObj obj){
		return new DemoObj(obj.getId()+1, obj.getName()+"yy");
	}
	
	@RequestMapping(value = "/getxml", produces={"application/xml;charset=UTF-8"})
	public DemoObj getxml(DemoObj obj){
		return new DemoObj(obj.getId()+1, obj.getName()+"yy");
	}

}
