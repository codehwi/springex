package com.mat.mvc.service.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.mat.mvc.dao.MemberDAO;
import com.mat.mvc.dao.MemberVO;
import com.mat.mvc.service.MemberService;
@Service
public class MemberServiceImpl implements MemberService {
	
	@Inject
	private MemberDAO dao;
	
	//회원가입
	@Override
	public void register(MemberVO vo) throws Exception {
		
		dao.register(vo);

	}

	@Override
	public MemberVO login(MemberVO vo) throws Exception {
		return dao.login(vo);

	}

	@Override
	public void update(MemberVO vo) throws Exception {
		dao.update(vo);
		
	}

	@Override
	public void delete(MemberVO vo) throws Exception {
		dao.delete(vo);
		
	}

	@Override
	public MemberVO IDCheck(String userID) throws Exception {
		return dao.IDCheck(userID);
	}

}
