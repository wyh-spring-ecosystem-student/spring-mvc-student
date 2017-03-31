package com.chenfeng.xiaolyuh.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chenfeng.xiaolyuh.entity.DemoObj;


@Controller // 声明此类是一个控制器
@RequestMapping("/anno") // 映射此类访问路径是/anno
public class DemoAnnoController {

	// 此方法没有标注路径，因此使用类级别的路径/auno，produces可以制订返回的response的媒体类型和字符集，
	// 或需返回值是json对象，则设置produces = "application/json;charset=UTF-8"
	@RequestMapping(produces = {"text/plain;charset=UTF-8", "application/json;charset=UTF-8"})	
	public @ResponseBody String index(HttpServletRequest request) { 
		return "url:" + request.getRequestURL() + " can access";
	}

	@RequestMapping(value = "/pathvar/{str}", produces = "text/plain;charset=UTF-8")
	public @ResponseBody String demoPathVar(@PathVariable String str,
			HttpServletRequest request) {
		return "url:" + request.getRequestURL() + " can access,str: " + str;
	}

	@RequestMapping(value = "/requestParam", produces = "text/plain;charset=UTF-8")
	public @ResponseBody String passRequestParam(Long id,
			HttpServletRequest request) {
		
		return "url:" + request.getRequestURL() + " can access,id: " + id;

	}

	@RequestMapping(value = "/obj", produces = "application/json;charset=UTF-8")
	@ResponseBody 
	public String passObj(DemoObj obj, HttpServletRequest request) {
		
		 return "url:" + request.getRequestURL() 
		 			+ " can access, obj id: " + obj.getId()+" obj name:" + obj.getName();

	}

	// 映射不用的路径到同一方法
	@RequestMapping(value = { "/name1", "/name2" }, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String remove(HttpServletRequest request) {
		
		return "url:" + request.getRequestURL() + " can access";
	}

}
