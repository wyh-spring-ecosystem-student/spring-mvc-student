package com.chenfeng.xiaolyuh.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.chenfeng.xiaolyuh.config.MvcConfig;
import com.chenfeng.xiaolyuh.service.DemoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MvcConfig.class })
@WebAppConfiguration("src/main/resources") // 注解在类上，用来声明加载的ApplicationContext是一个WebApplicationContext。他的属性制定的是Web资源的位置，默认是src/main/webapp。
public class TestControllerIntegrationTests {
	private MockMvc mockMvc; // 模拟MVC对象，通过MockMvcBuilders.webAppContextSetup(this.wac).build()初始化。

	@Autowired
	private DemoService demoService; // 可以在测试用例中注入Spring Bean

	@Autowired
	private WebApplicationContext wac; // 注入WebApplicationContext

	@Autowired
	private MockHttpSession session;// 注入模拟的http session
	
	@Autowired
	private MockHttpServletRequest request;// 注入模拟的http request\

	@Before // 在测试开始前初始化工作
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void testNormalController() throws Exception {
		mockMvc.perform(get("/normal"))// 模拟向/normal进行get请求
				.andExpect(status().isOk())// 预期控制返回状态是200
				.andExpect(view().name("page"))// 预期view名称是page
				.andExpect(forwardedUrl("/WEB-INF/classes/views/page.jsp"))// 预期页面跳转的真正路劲是/WEB-INF/classes/views/page.jsp
				.andExpect(model().attribute("msg", demoService.saySomething())); // 预期model里的值是demoService.saySomething()的返回值
	}

	@Test
	public void testRestController() throws Exception {
		MvcResult result = mockMvc.perform(get("/testRest")).andExpect(status().isOk())// 模拟向testRest发送get请求
				.andExpect(content().contentType("text/plain;charset=UTF-8"))// 预期返回值的媒体类型text/plain;charset=UTF-8
				.andExpect(content().string(demoService.saySomething()))// 预期返回值类容是demoService.saySomething()
				.andReturn();// 返回执行请求的结果
		
		System.out.println(result.getResponse());
	}
}
