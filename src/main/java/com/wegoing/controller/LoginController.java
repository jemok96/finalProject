package com.wegoing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wegoing.dto.MemberDTO;
import com.wegoing.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LoginController {
	
	
	
	@Autowired
	private MemberService memberService;

	@GetMapping("/register")
	public String registerForm(Model model) {
		return "home/register";
	}
	
	
	
	@PostMapping("/register") // insert
	public String registerOk(@ModelAttribute("dto")MemberDTO dto) {
		log.info("<<<<<<<<<<<<<<<<<<< postmapping");
		log.info("<<<<<<<<<<<<<<<<<<< email" + dto.getEmail());
		
		memberService.addOne(dto);
		return "redirect:/main";
	}
	
	@GetMapping("/login")
	public String login() {
		return "home/login";
	}
	
	@GetMapping("/logout")
	public String logout() {
		return "redirect:/";
	}
}
