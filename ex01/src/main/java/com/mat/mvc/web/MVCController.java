package com.mat.mvc.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mat.mvc.service.MVCService;

@Controller
public class MVCController {
	
	@Autowired
	MVCService mvcservice;
	
	@RequestMapping("/index")
	public String sayHello(Model model) throws Exception {
		model.addAttribute("Hello", mvcservice.sayHello());
		
		return "index";
	}
}
