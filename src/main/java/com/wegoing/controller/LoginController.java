package com.wegoing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/wegoing")
public class LoginController {

	@GetMapping("/register")
	public String registerForm() {
		return "home/register";
	}
}
