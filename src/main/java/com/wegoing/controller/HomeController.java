package com.wegoing.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.wegoing.dto.ClubDTO;
import com.wegoing.dto.PrincipalDetails;
import com.wegoing.service.ClubMemberService;
import com.wegoing.service.ClubService;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {

	private ClubMemberService cms; 
	
	@Autowired
	public HomeController(ClubMemberService cmservice) {
		this.cms = cmservice; 
	}
	
	@GetMapping("/main")
	public String main(@AuthenticationPrincipal PrincipalDetails userDetails, Model model) {
		model.addAttribute("user", userDetails.getMdto());
		String loginEmail =""; 
		if( userDetails != null ) loginEmail = userDetails.getMdto().getEmail();
		List<ClubDTO> myClub= cms.selectAll(loginEmail);
		model.addAttribute("myClub", myClub); // 여기까지

		System.out.println("main돌아옴");
		return "home/mainpage";
	}
	
}
