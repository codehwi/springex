package com.mat.mvc.web;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mat.mvc.dao.MemberVO;
import com.mat.mvc.service.MemberService;

@Controller
@RequestMapping("/member/*")
public class MemberController {

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(MemberController.class);
	@Inject
	MemberService service;
	
	@Autowired
	BCryptPasswordEncoder passEncoder;

	// 게시물 읽어오기
	@RequestMapping(value = "register", method = RequestMethod.GET)
	public void getRegister(Model model) throws Exception {
		logger.info("get register");

	}

	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String postRegister(MemberVO vo) throws Exception {
		logger.info("post register");
		String inputPass=vo.getUserPW();
		String pass=passEncoder.encode(inputPass);
		vo.setUserPW(pass);
		service.register(vo);
		return "redirect:/";
	}
	@Bean
	BCryptPasswordEncoder PasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String postlogin(MemberVO vo, HttpServletRequest req, RedirectAttributes rttr) throws Exception {
		logger.info("post login");
		HttpSession session = req.getSession();//세션 받아와서
		MemberVO login = service.login(vo);//맞는 userID,userName,userPW 반환
		boolean passMatch=passEncoder.matches(vo.getUserPW(), login.getUserPW());
		if (login != null&&passMatch) {
			session.setAttribute("member", login);
			

		} else {
			session.setAttribute("member", null);
			rttr.addFlashAttribute("msg", false);
			System.out.println("로그인성공");
		}
//		if (login == null) {
//			session.setAttribute("member", null);
//			rttr.addFlashAttribute("msg", false);
//			System.out.println("없는아이디");
//
//		} else {
//			session.setAttribute("member", login);
//			System.out.println("로그인성공");
//		}
		return "redirect:/index";

	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) throws Exception {
		logger.info("get logout");
		session.invalidate();
		return "redirect:/";

	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public void getUpdate() throws Exception {
		logger.info("get update");

	}
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String postUpdate(MemberVO vo,HttpSession session) throws Exception {
		logger.info("post update");
		service.update(vo);
		session.invalidate();
		return "redirect:/";

	}
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public void getDelete() throws Exception {
		logger.info("get delete");

	}
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String postDelete(MemberVO vo,HttpSession session,RedirectAttributes rttr) throws Exception {
		logger.info("post update");
		
		MemberVO member=(MemberVO)session.getAttribute("member");
		String oldPW=member.getUserPW();
		String newPW=vo.getUserPW();
		if(!(oldPW.contentEquals(newPW))) {
			rttr.addFlashAttribute("msg",false);
			return "redirect:/member/delete";
		}
		service.delete(vo);
		logout(session);
		return "redirect:/";

	}
	@RequestMapping(value = "/IDCheck", method = RequestMethod.GET)
	public void getIDCheck() throws Exception {
		logger.info("get idcheck");

	}
	@ResponseBody
	@RequestMapping(value = "/IDCheck", method = RequestMethod.POST)
	public int postIDCheck(HttpServletRequest req) throws Exception {
		logger.info("post idcheck");
		String userID=req.getParameter("userID");
		MemberVO IDCheck=service.IDCheck(userID);
		int result=0;
		if(IDCheck!=null) {
			result=1;
		}
		return result;
	}
}
