package com.wegoing.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.wegoing.dto.PrincipalDetails;
import com.wegoing.util.ClubUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {

//	@Resource(name="clubUtil")
//	private clubUtil clubUtil; 
	
	@GetMapping("/main")
	public String main(@AuthenticationPrincipal PrincipalDetails userDetails, Model model) {
		model.addAttribute("user", userDetails.getMdto());
		model.addAttribute("myClub", ClubUtil.getClub(userDetails)); 
		return "home/mainpage";
	}
}
