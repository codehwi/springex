package com.mat.mvc.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mat.mvc.dao.ReplyDAO;
import com.mat.mvc.dao.ReplyVO;
@Repository
public class ReplyDAOImpl implements ReplyDAO {
	@Inject
	private SqlSession sql;

	private static String namespace = "com.board.mappers.reply";

	
	@Override
	 public List<ReplyVO> readReply(int bno) throws Exception{
		
	
	return sql.selectList(namespace + ".readReply", bno);

}


	@Override
	public void writeReply(ReplyVO vo) throws Exception {
		sql.insert(namespace+".writeReply", vo);
		
	}

	@Override
	public void deleteReply(ReplyVO vo) throws Exception {
		sql.delete(namespace+".deleteReply",vo);
		
	}


	@Override
	public void updateReply(ReplyVO vo) throws Exception {
		sql.update(namespace+".updateReply",vo);
		
	}
}