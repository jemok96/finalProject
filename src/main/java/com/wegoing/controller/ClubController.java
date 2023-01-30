package com.wegoing.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	
	@Autowired
	public ClubController(ClubService clubservice , ClubMemberService clubmemberservice) {
		this.cs = clubservice; 
		this.cms = clubmemberservice;
	}
		
	@PostMapping("/club")
	public String createClub(@RequestParam("clname")String name, @RequestParam("clexplain")String explain, 
			@ModelAttribute("cdto")ClubDTO cdto,@ModelAttribute("cmdto")ClubMemberDTO cmdto, Model model, @AuthenticationPrincipal PrincipalDetails userDetails) {
		// 클럽테이블에 insert
		log.info("here");	
		log.info(name);
		log.info(explain);
		cdto.setClname(name);
		cdto.setClexplain(explain);	
		cs.addClub(cdto);
		
		// Autoincrement된 clno값 클럽멤버 테이블에 set하기위해 가져오기
		long clno= cdto.getClno(); 
		System.out.println(clno);
		
		// 세션에 나에 대한 이메일(pk)가져오기
		String loginEmail = ""; 
		log.info(" principal : {} " , userDetails);
		if( userDetails != null ) loginEmail = userDetails.getMdto().getEmail();
		System.out.println("email" +loginEmail);
		
		// 클럽멤버 테이블에 insert 
		cmdto.setEmail(loginEmail);
		cmdto.setCrank("host"); 
		cmdto.setCstatus("y");
		cmdto.setClno(clno);
		cms.addClubMember(cmdto);
		
		return "redirect:/main";
	}

	
}
