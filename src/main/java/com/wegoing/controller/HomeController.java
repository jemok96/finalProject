package com.wegoing.controller;

import java.security.Principal;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wegoing.dto.PrincipalDetails;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {
	@GetMapping("/main")
	public String main(@AuthenticationPrincipal PrincipalDetails userDetails, Model model) {
		model.addAttribute("user", userDetails.getMdto());
		return "home/mainpage";
	}
	
	@GetMapping("/partner")
	public String partnerForm() {
		return "partner/partnerForm";
	}
	
	@PostMapping("/partner")
	public String submitForm() {
		return "partner/partnerForm";
	}
}
