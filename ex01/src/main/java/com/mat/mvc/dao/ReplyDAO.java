package com.mat.mvc.dao;

import java.util.List;

public interface ReplyDAO {

	public List<ReplyVO> readReply(int bno) throws Exception;
	
	public void  writeReply(ReplyVO vo) throws Exception;
	
	public void deleteReply(ReplyVO vo) throws Exception;
	
	public void updateReply(ReplyVO vo) throws Exception;

}
