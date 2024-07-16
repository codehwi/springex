package com.mat.mvc.web;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mat.mvc.dao.BoardVO;
import com.mat.mvc.dao.MemberVO;
import com.mat.mvc.dao.Page;
import com.mat.mvc.dao.ReplyVO;
import com.mat.mvc.service.BoardService;
import com.mat.mvc.service.ReplyService;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	@Inject
	BoardService service;
	@Inject
	ReplyService repservice;
	//게시물 읽어오기
	@RequestMapping(value="list",method=RequestMethod.GET)
	public void getList(Model model) throws Exception{
		
		List list=null;
		list=service.list();
		model.addAttribute("list", list);
		
		
	}
	//게시물작성
	
	@RequestMapping(value="/write",method=RequestMethod.GET)
	public void getWrite(HttpSession session,Model model) throws Exception{
		Object loginInfo=session.getAttribute("member");
		if(loginInfo==null) {
			model.addAttribute("msg","login_error");
		}
	
		
	}
	@RequestMapping(value="/write",method=RequestMethod.POST)
	public String postWrite(BoardVO vo) throws Exception{
		service.write(vo);
		
		return "redirect:/board/list";
	}
	
	@RequestMapping(value="/replyWrite", method=RequestMethod.POST)
	public String postReply(HttpSession session,ReplyVO vo, RedirectAttributes rttr,Model model,HttpServletRequest req) throws Exception {
		Object loginInfo = session.getAttribute("member");
		rttr.addAttribute("bno", vo.getBno());
	    if (loginInfo == null) {
	    	model.addAttribute("msg","로그인 후 작성가능합니다");
	    	return "forward:/board/view";
	    			}else {
		repservice.writeReply(vo);

	    

	    return "redirect:/board/view";}
	    
	}

	@RequestMapping(value="/view",method=RequestMethod.GET)
	public void getView(@RequestParam("bno") int bno,Model model) throws Exception{
		
		BoardVO vo= service.view(bno);
		model.addAttribute("view",vo);
		
		List<ReplyVO> repList = repservice.readReply(bno);
		model.addAttribute("repList",repList);
	}
	
	@RequestMapping(value = "/deleteReply", method = RequestMethod.GET)
	public void getDelete() throws Exception {

	}
	
	@RequestMapping(value = "/deleteReply", method = RequestMethod.POST)
	public String postDelete(@RequestParam("rno") int rno, @RequestParam("bno") int bno, RedirectAttributes rttr) throws Exception {
	    ReplyVO vo = new ReplyVO();
	    vo.setRno(rno);
	    vo.setBno(bno);

	    repservice.deleteReply(vo);
	    return "redirect:/board/view?bno=" + bno;
	}

	@RequestMapping(value="/modify",method=RequestMethod.GET)
	public void getModify(@RequestParam("bno") int bno,Model model) throws Exception{
		
		BoardVO vo= service.view(bno);
		model.addAttribute("view",vo);
	}
	@RequestMapping(value="/modify",method=RequestMethod.POST)
	public String getModify(BoardVO vo) throws Exception{
		
		service.modify(vo);
		return "redirect:/board/view?bno="+vo.getBno();
	}
	

	@RequestMapping(value = "/updateReply", method = RequestMethod.POST)
	public String postUpdate(ReplyVO vo, @RequestParam("bno") int bno, RedirectAttributes rttr) throws Exception {
	    repservice.updateReply(vo);
	    rttr.addAttribute("bno", bno);
	    return "redirect:/board/view";
	}

	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public String getDelete(int bno) throws Exception{
			service.delete(bno);
			return "redirect:/board/list";
		
	}
//	@RequestMapping(value="/listPage",method=RequestMethod.GET)
//	public void getListPage(Model model) throws Exception{
//		List list=null;
//		list=service.list();
//		model.addAttribute("list", list);
//	}
//	
	@RequestMapping(value="/listPage",method=RequestMethod.GET)
	public void getListPage(Model model,@RequestParam("num") int num) throws Exception{
		Page page=new Page();
		
		page.setNum(num);
		page.setCount(service.count());
		
		List list=null;
		list=service.listPage(page.getDisplayPost(), page.getPostNum());
		model.addAttribute("list", list);
		model.addAttribute("page",page);
		model.addAttribute("select",num);
		

		

	}
	@RequestMapping(value="/listPageSearch",method=RequestMethod.GET)
	public void getListPageSearch(Model model,@RequestParam("num") int num,
			@RequestParam(value="searchType",required=false,defaultValue="title") String searchType,
			@RequestParam(value="keyword",required=false,defaultValue="") String keyword
			) throws Exception{
		Page page=new Page();
		
		page.setNum(num);
		page.setCount(service.searchCount(searchType,keyword));
		//검색 타입과 검색어
		page.setSearchTypeKeyword(searchType, keyword);
		
		List list=null;
		list=service.listPageSearch(page.getDisplayPost(), page.getPostNum(),searchType,keyword);
		model.addAttribute("list", list);
		model.addAttribute("page",page);
		model.addAttribute("select",num);	
		
		model.addAttribute("searchType", searchType);
		model.addAttribute("keyword", keyword);

	}
	
	
	

}
