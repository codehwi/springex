package com.mat.mvc.web;

import org.slf4j.LoggerFactory;
import java.io.*;
import java.io.IOException;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.File;





@Controller
@RequestMapping("/board/*")
public class UploadController {
	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);
	@Resource(name="uploadPath")
		private String uploadPath;
	
	@RequestMapping(value="/uploadForm",method=RequestMethod.GET)
	public void uplaodForm() {
		
	}
	@RequestMapping(value="/uploadForm",method=RequestMethod.POST)
	public String uplaodForm(MultipartFile file,Model model) throws Exception{
		logger.info("originalName: " +file.getOriginalFilename());
		logger.info("size: " +file.getSize());
		logger.info("contentType : " +file.getContentType());
		String savedName= uploadFile(file.getOriginalFilename(),file.getBytes());
		model.addAttribute("savedName",savedName);
		
		return "uploadResult";
		
	}
	 private String uploadFile(String originalName, byte[] fileData)throws Exception{
	      UUID uid = UUID.randomUUID();
	      
	      String savedName = uid.toString() + "_" + originalName;
	      
	      java.io.File target = new java.io.File(uploadPath, savedName);
	      
	      FileCopyUtils.copy(fileData, target);
	      
	      return savedName;
	   }
	 //AJAX
	 @RequestMapping(value="/uploadAjax",method=RequestMethod.GET)
		public String uplaodAjax() throws Exception{
			return "/board/uploadAjax";
			
		}
	
}
