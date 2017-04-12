package com.chenfeng.xiaolyuh.message.converter;

import java.io.IOException;
import java.nio.charset.Charset;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;

import com.chenfeng.xiaolyuh.entity.DemoObj;

/**
 * 继承AbstractHttpMessageConverter类来实现自定义的HttpMessageConverter（消息转换器）
 */
public class MyHttpMessageConverter extends AbstractHttpMessageConverter<DemoObj> {

	/**
	 * 
	 * @Description 建立我们自定义的媒体类型"application/x-wisely"
	 */
	public MyHttpMessageConverter() {
		super(new MediaType("application", "x-wisely", Charset.forName("UTF-8")));
	}
	
	/**
	 * 表明HttpMessageConverter（消息转换器）只处理DemoObj类
	 */
	@Override
	protected boolean supports(Class<?> clazz) {
		// 判断clazz类是不是DemoObj类或者是DemoObj的子类
		return DemoObj.class.isAssignableFrom(clazz);
	}

	/**
	 * 重写readInternal方法，处理请求数据。代码表明我们处理有“-”隔开的数据，并转成DemoObj对象
	 */
	@Override
	protected DemoObj readInternal(Class<? extends DemoObj> clazz, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {
		
		String temp = StreamUtils.copyToString(inputMessage.getBody(), Charset.forName("UTF-8"));
		String[] tempArr = temp.split("-");
		return new DemoObj(Long.parseLong(tempArr[0]), tempArr[1]);
	}

	/**
	 * 重写writeInternal方法，处理数据如何输出到response。此处，我么在原样输出前面加上“HELLO”
	 */
	@Override
	protected void writeInternal(DemoObj t, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		
		String out = "HELLO:" + t.getId() + "-" + t.getName();
		outputMessage.getBody().write(out.getBytes());
	}

}
