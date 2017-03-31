package com.chenfeng.xiaolyuh.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 可以让普通的Bean实现HandlerInterceptor接口或者继承HandlerInterceptorAdapter类来实现自定义拦截器。
 * 这里通过继承HandlerInterceptorAdapter类来实现。
 * @author yuhao.wang
 * @Date 2017年3月31日 下午5:29:32
 */
@Component // 声明成一个Bean
public class DemoInterceptor extends HandlerInterceptorAdapter {
	
	// 请求发生之前执行
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		request.setAttribute("startTime", System.currentTimeMillis());
		System.out.println("请求之前执行");
		return true;
	}
	
	// 请求发生之后执行
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		Long startTime = (Long)request.getAttribute("startTime");
		request.removeAttribute("startTime");
		Long endTIme = System.currentTimeMillis();
		System.out.println("请求之后执行");
		System.out.println("本次请求的时间是：" + (endTIme - startTime) + "ms");
	}
}
