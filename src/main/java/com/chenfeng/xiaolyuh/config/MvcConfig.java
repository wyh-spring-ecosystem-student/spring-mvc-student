package com.chenfeng.xiaolyuh.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.chenfeng.xiaolyuh.interceptor.DemoInterceptor;
import com.chenfeng.xiaolyuh.message.converter.MyHttpMessageConverter;

/**
 * MVC 配置类。这里我们配置了一个jsp的ViewResolver，用来映射路径和实际页面的位置，
 * 其中@EnableWebMvc注解会开启一些默认的配置，如一些ViewResolver或者MessageConverter。
 * 
 * Spring MVC的定制配置需要我们的配置类继承WebMvcConfigurerAdapter类（Adapter适配器），
 * 并在此类加上@EnableWebMvc注解，来开启对Spring MVC的配置支持，这样我们就可以重写这个类的方法，
 * 来完成我们的配置。如果不加@EnableWebMvc注解，重写这些方法也无效。
 * @author yuhao.wang
 * @Date 2017年3月29日 下午3:41:20
 */
@Configuration
@EnableWebMvc// 开启对Spring MVC的支持，如果不加@EnableWebMvc注解，重写这些方法也无效。
@ComponentScan("com.chenfeng.xiaolyuh")
public class MvcConfig extends WebMvcConfigurerAdapter {// 重写WebMvcConfigurerAdapter类的方法可以对Spring MVC
	
	@Autowired
	private DemoInterceptor demoInterceptor;
	
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/classes/views/");// 运行时代码会将页面自动编译到/WEB-INF/classes/views/下
		viewResolver.setSuffix(".jsp");
		viewResolver.setViewClass(JstlView.class);
		return viewResolver;
	}
	
	@Bean
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(10000000);
		multipartResolver.setDefaultEncoding("UTF-8");
		return multipartResolver;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// addResourceLocations是指文件放置的目录，addResourceHandler是指对外暴露的地址
		registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/assets/");
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 注册拦截器
		registry.addInterceptor(demoInterceptor).addPathPatterns("/**");
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// 重定向
		registry.addRedirectViewController("index3", "index");
		// 直接配置请求路径（index3）和页面（index.jsp）之间的映射。
		// 无任何业务逻辑只是简单的业务逻辑可以这样子写，代码更简洁，管理集中
		registry.addViewController("index2").setViewName("index");
		registry.addViewController("forUpload").setViewName("upload");
		registry.addViewController("converter").setViewName("converter");
		
	}
	
	@Bean
	public MyHttpMessageConverter converter() {
		return new MyHttpMessageConverter();
	}
	
	/**
	 * 覆盖掉Spring MVC默认注册的多个HttpMessageConverter
	 */
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		
		super.configureMessageConverters(converters);
	}
	
	/**
	 * 仅添加一个自定义的HttpMessageConverter，不覆盖默认的HttpMessageConverter
	 */
	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		// 添加一个自定义的消息转换
		converters.add(converter());
	}
	
	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		// 设置URL中如果带有【.】的话，不忽略【.】后面的东西
		configurer.setUseSuffixPatternMatch(false);
	}
}
