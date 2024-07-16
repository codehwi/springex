package com.mat.mvc.service;

import com.mat.mvc.dao.MemberVO;

public interface MemberService {
	public void register(MemberVO vo) throws Exception;
	
	public MemberVO login(MemberVO vo) throws Exception;
	
	public void update(MemberVO vo) throws Exception;
	
	public void delete(MemberVO vo) throws Exception;
	
	public MemberVO IDCheck(String userID) throws Exception;
}
