package com.wegoing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class home {
	@GetMapping("/home")
	public String hello() {
		return "/views/home";
	}
}
