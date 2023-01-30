package com.wegoing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wegoing.service.EmailService;
import com.wegoing.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	

	@PostMapping("/emailCheck")
	@ResponseBody
	public int emailCheck(@RequestParam("email")String email) {
		log.info("<<<<<<<<<<<<<<<< emailCheck()" + email);
		int cnt = memberService.emailCheck(email);
		log.info("<<<<<<<<<<<<<<<< cnt : "+cnt);
//		cnt =1;
		return cnt;
	}
	
	@PostMapping("/nicknameCheck")
	@ResponseBody
	public int nicknameCheck(@RequestParam("nickname")String nickname) {
		log.info("<<<<<<<<<<<<<<<< nicknameCheck()" + nickname);
		int cnt = memberService.nicknameCheck(nickname);
		log.info("<<<<<<<<<<<<<<<< cnt : "+cnt);
		
		return cnt;
	}
	
	
	
	
	
	
}
