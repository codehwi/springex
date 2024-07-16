package com.mat.mvc.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.mat.mvc.dao.ReplyDAO;
import com.mat.mvc.dao.ReplyVO;
import com.mat.mvc.service.ReplyService;
@Repository
public class ReplyServiceImpl implements ReplyService {
	
	@Inject
	private ReplyDAO dao;
	@Override
	public List<ReplyVO> readReply(int bno) throws Exception {
		return dao.readReply(bno);
	}
	@Override
	public void writeReply(ReplyVO vo) throws Exception {
		dao.writeReply(vo);
		
	}
	@Override
	public void deleteReply(ReplyVO vo) throws Exception {
		dao.deleteReply(vo);
		
	}
	@Override
	public void updateReply(ReplyVO vo) throws Exception {
		dao.updateReply(vo);
		
	}

}
