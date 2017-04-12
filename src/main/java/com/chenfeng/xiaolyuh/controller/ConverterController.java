package com.chenfeng.xiaolyuh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chenfeng.xiaolyuh.entity.DemoObj;


@Controller // 声明是一个控制器，并且返回时不需要@ResponseBody
public class ConverterController {
	
	@RequestMapping(value = "/convert", produces = { "application/x-wisely" })// 自定义的媒体类型
	@ResponseBody
    public DemoObj convert(@RequestBody DemoObj demoObj) {
		
        return demoObj;
    }

}
