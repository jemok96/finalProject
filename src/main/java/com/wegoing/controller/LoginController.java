package com.wegoing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LoginController {

	@GetMapping("/register")
	public String registerForm(Model model) {
		return "home/register";
	}
	
	@PostMapping("/register")
	public String registerOk() {
		log.info("<<<<<<<<<<<<<<<<<<< postmapping");
		// mapper 통해서 DB에 insert하기
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
