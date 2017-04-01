package com.chenfeng.xiaolyuh.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice// 声明一个控制器建言，@ControllerAdvice组合了@Component注解，所以自动注册成Spring的一个Bean。
public class HandlerAdvice {
	Logger log = LoggerFactory.getLogger(HandlerAdvice.class);
	
	@ExceptionHandler(value = Exception.class)// 全局异常处理，可以通过value设置全局拦截条件
	public ModelAndView exception(Exception exception, WebRequest request) {
		ModelAndView modelAndView = new ModelAndView("error");// 跳转到Error页面
		modelAndView.addObject("errorMessage", exception.getMessage());
		log.debug(exception.getMessage(), exception);
		return modelAndView;
	}
	
	@ModelAttribute
	public void addAttributes(Model model) {
		
		//使用@ModelAttribute注解将键值对添加到全局，所有注解了@RequestMapping的地方都可以获得此键值对
		model.addAttribute("msg", "额外信息"); 
	}

	@InitBinder //通过注解定制WebDataBinder
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.setDisallowedFields("id"); //设置，忽略request参数的id
	}
}
