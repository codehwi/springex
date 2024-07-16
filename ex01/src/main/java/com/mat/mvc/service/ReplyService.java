package com.mat.mvc.service;

import java.util.List;

import com.mat.mvc.dao.ReplyVO;

public interface ReplyService {
	
	 public List<ReplyVO> readReply(int bno) throws Exception;
	 
	 public void  writeReply(ReplyVO vo) throws Exception;
	 
	 public void deleteReply(ReplyVO vo) throws Exception;
	 
	 public void updateReply(ReplyVO vo) throws Exception;

}
