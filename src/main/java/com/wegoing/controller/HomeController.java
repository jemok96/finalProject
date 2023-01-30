package com.wegoing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {
	@GetMapping("/main/test")
	public String main() {
		
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
