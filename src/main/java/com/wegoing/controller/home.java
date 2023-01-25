package com.wegoing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.wegoing.dto.MemberDTO;
import com.wegoing.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class home {
	
	private final MemberService service;
	
	@GetMapping("/home")
	public String hello(Model model) {
		
		List<MemberDTO> dto = service.select();
		System.out.println(dto);
		
		model.addAttribute("member",dto);
		return "/views/home";
	}
}
