package com.mat.mvc.service.impl;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.mat.mvc.dao.MemberDAO;
import com.mat.mvc.dao.MemberVO;
@Service
public class MemberDAOImpl implements MemberDAO{
	
	@Inject
	private SqlSession sql;

	private static String namespace = "com.board.mappers.member";

	@Override
	
	public void register(MemberVO vo) throws Exception {
		sql.insert(namespace+".register",vo);
		
	}

	@Override
	public MemberVO login(MemberVO vo) throws Exception {
		return sql.selectOne(namespace+".loginBcrypt", vo);
	}

	@Override
	public void update(MemberVO vo) throws Exception {
		sql.update(namespace+".update", vo);
		
	}

	@Override
	public void delete(MemberVO vo) throws Exception {
		sql.delete(namespace+".delete",vo);
		
	}

	@Override
	public MemberVO IDCheck(String userID) throws Exception {
		return sql.selectOne(namespace+".IDCheck",userID);
	}

}
