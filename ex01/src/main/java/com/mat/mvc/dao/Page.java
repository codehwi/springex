package com.mat.mvc.dao;


public class Page {
	
	private int num;
	// 게시물총량
	private int count;
	// 한페이지에 출력할 게시물
	int postNum = 10;
	// 하단 페이징번호(총갯수+한페이지의 출력할갯수 의 올림)
	private int pageNum;
	// 출력할 게시물
	private int displayPost;

	// 한번에 표시할 게시물
	private int pageNum_cnt = 10;
	// 표시되는 페이지 마지막번호
	private int endPageNum;
	// 표시되는 페이지 첫번쨰 번호
	private int startPageNum = endPageNum - (pageNum_cnt - 1);

	private boolean prev;
	private boolean next;
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
		dataCalc();
	}
	public int getPostNum() {
		return postNum;
	}
	public int getPageNum() {
		return pageNum;
	}
	public int getDisplayPost() {
		return displayPost;
	}
	public int getPageNum_cnt() {
		return pageNum_cnt;
	}
	public int getEndPageNum() {
		return endPageNum;
	}
	public int getStartPageNum() {
		return startPageNum;
	}
	public boolean isPrev() {
		return prev;
	}
	public boolean isNext() {
		return next;
	}

	private void dataCalc() {
	endPageNum=(int)(Math.ceil((double)num/(double)pageNum_cnt)*pageNum_cnt);
	startPageNum=endPageNum-(pageNum_cnt-1);
	int endPageNum_tmp=(int)(Math.ceil((double)count/(double)pageNum_cnt));
	if(endPageNum>endPageNum_tmp) {
		endPageNum=endPageNum_tmp;
	}
	prev=startPageNum==1?false:true;
	next=endPageNum*pageNum_cnt>=count?false:true;
	
	displayPost=(num-1)*postNum;
	
	}
	private String searchTypeKeyword;
	
	public void setSearchTypeKeyword(String searchType,String keyword) {
		if(searchType.equals("")||keyword.equals("")) {
			searchTypeKeyword="";
			
		}else {
			searchTypeKeyword="&amp;searchType="+searchType+"&amp;keyword="+keyword;
		}
	}
	public String getSearchTypeKeyword() {
		return searchTypeKeyword;
	}

}
