package com.wegoing.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wegoing.dao.ClubMemberDAO;
import com.wegoing.dto.ClubDTO;
import com.wegoing.dto.ClubMemberDTO;
import com.wegoing.dto.PrincipalDetails;
import com.wegoing.service.ClubMemberService;
import com.wegoing.service.ClubService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ClubController {
	private ClubService cs;
	private ClubMemberService cms; 
	String loginEmail = ""; 
	
	@Autowired
	public ClubController(ClubService clubservice , ClubMemberService clubmemberservice) {
		this.cs = clubservice; 
		this.cms = clubmemberservice;
	}
		
	@PostMapping("/club")
	public String createClub(@RequestParam("clname")String name, @RequestParam("clexplain")String explain, 
			@ModelAttribute("cdto")ClubDTO cdto,@ModelAttribute("cmdto")ClubMemberDTO cmdto,  
			@AuthenticationPrincipal PrincipalDetails userDetails, HttpServletRequest request) {
		// 클럽테이블에 insert
		cdto.setClname(name);
		cdto.setClexplain(explain);	
		cs.addClub(cdto);
		
		// Autoincrement된 clno값 클럽멤버 테이블에 set하기위해 가져오기
		long clno= cdto.getClno(); 
		
		// 세션에 나에 대한 이메일(pk)가져오기
		if( userDetails != null ) loginEmail = userDetails.getMdto().getEmail();
		
		// 클럽멤버 테이블에 insert 
		cmdto.setEmail(loginEmail);
		cmdto.setCrank("host"); 
		cmdto.setCstatus("y");
		cmdto.setClno(clno);
		cms.addClubMember(cmdto);
		
		// 협업공간생성 모달에서 만들기 버튼 클릭하면 해당 html에 멈물수 있도록 return 
		if (request.getHeader("Referer") != null) {
		    return "redirect:" + request.getHeader("Referer");
		  } else {
		    return "redirect:/main";
		  }
	}
	
	@GetMapping("/club/{clno}")
	public String clubMain(@PathVariable int clno, Model model) {
		// 협업공간 이름, 설명, 멤버
		// 협업공간 생성자(host)와 사용자(user.mail)이 동일하면 설정 버튼 출력
		ClubDTO cdto = cs.getOne(clno);
		log.info("cdto >>>>> " + cdto);
		model.addAttribute("club", cdto);
		
		return "club/clubMain";
	}
	
	@PostMapping("/club/{clno}/edit")
	public String editClub(@PathVariable int clno, @RequestParam("editClname")String editClname,
							@RequestParam("editClexplain")String editClexplain, Model model) {
		log.info("clno >>>>>" + clno);
		ClubDTO cdto = cs.getOne(clno);
		model.addAttribute("club", cdto);
		
		cdto.setClname(editClname);
		cdto.setClexplain(editClexplain);
		
		log.info("수정 후 이름 >>>>>" + editClname);
		log.info("수정 후 내용 >>>>>" + editClexplain);
		
		cs.modifyOne(cdto);
		
		return "redirect:/club/{clno}";
	}
	
}
