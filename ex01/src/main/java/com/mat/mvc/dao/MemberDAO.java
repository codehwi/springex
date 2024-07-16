package com.mat.mvc.dao;

public interface MemberDAO {
	//회원가입
	public void register(MemberVO vo) throws Exception;
	
	public MemberVO login(MemberVO vo) throws Exception;
	
	public void update(MemberVO vo) throws Exception;
	
	public void delete(MemberVO vo) throws Exception;
	
	public MemberVO IDCheck(String userID) throws Exception;

}
