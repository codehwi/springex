package com.mat.mvc.dao;

import java.util.List;

public interface BoardDAO {
   
   //게시물 읽기
   public List<BoardVO> list() throws Exception;
   
   //게시물 작성
   public void  write(BoardVO vo) throws Exception;
   
   //게시물 보기
   public BoardVO view(int bno) throws Exception;
   //게시물 수정
   public void modify(BoardVO vo) throws Exception;
   //게시물 삭제
   public void delete(int bno) throws Exception;
   //게시물 총 갯수
   public int count() throws Exception;
   
   public List listPage(int displayPost,int postNum) throws Exception;
   
   public List<BoardVO> listPageSearch(int displayPost,int postNum,String searchType,String keyword) throws Exception;
   
   public int searchCount(String searchType,String keyword) throws Exception;
   
   public void viewCnt(int bno) throws Exception;
   
   
   
}