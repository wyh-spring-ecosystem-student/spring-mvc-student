package com.chenfeng.xiaolyuh.controller;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController // 声明是一个控制器，并且返回时不需要@ResponseBody
public class FileUploadController {
	
	@RequestMapping(value = "upload", method = RequestMethod.POST) 
	public Object getjson (MultipartFile file) throws IOException{
		FileUtils.writeByteArrayToFile(new File("E:/upload/" + file.getOriginalFilename()), file.getBytes());
		
		return "OK";
	}
}
