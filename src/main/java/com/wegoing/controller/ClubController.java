package com.wegoing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	private ClubMemberService cms; 
	
	@Autowired
	public ClubController(ClubService clubservice ) {
		this.cs = clubservice; 
	}
	
	@GetMapping("/club")
	public String createClub(@RequestParam("clname")String name, @RequestParam("clexplain")String explain, 
			@ModelAttribute("cdto")ClubDTO cdto, @ModelAttribute("cmdto")ClubMemberDTO cmdto) {
		log.info("here");	
		log.info(name);
		log.info(explain);
		cdto.setClname(name);
		cdto.setClexplain(explain);
		log.info("dd");
		cs.addClub(cdto);
		return "club/club";
	}

	
}
