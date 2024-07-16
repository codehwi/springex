package com.mat.mvc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mat.mvc.dao.MVCDao;
import com.mat.mvc.service.MVCService;

@Service
public class MVCServiceImpl implements MVCService {

	@Autowired
	private MVCDao mvcdao;
	
	@Override
	public String sayHello() {
		System.out.println(mvcdao.sayHello()+"================================");
		return "안녕";
	}
	
}