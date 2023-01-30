package com.wegoing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wegoing.dto.ClubDTO;
import com.wegoing.dto.ClubMemberDTO;
import com.wegoing.service.ClubMemberService;
import com.wegoing.service.ClubService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ClubController {
	private ClubService cs;
	
	String email = "aaa@gmail.com";
	
	@Autowired
	public ClubController(ClubService clubservice ) {
		this.cs = clubservice; 
	}
	
	
	@PostMapping("/club")
	public String createClub(@RequestParam("clname")String name, @RequestParam("clexplain")String explain, 
			@ModelAttribute("cdto")ClubDTO cdto, Model model) {
		log.info("here");	
		log.info(name);
		log.info(explain);
		cdto.setClname(name);
		cdto.setClexplain(explain);	
		cs.addClub(cdto);
		
		// 클럽멤버디티오에도 값이 들어간다.
		
		// 1. 
		// 거기서 리뉴얼 된 값을 가지고 최근 가입 순에 먼저 보임 
		// 내 협업공간 리스트에도 보임 
		
		// 2.club의 clno값을 가져와서 같이 넘기는 방법이 잇을까/? 
		
		return "redirect:/main";
	}

	
}
