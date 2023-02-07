package com.wegoing.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.wegoing.dto.NoticeDTO;
import com.wegoing.dto.PrincipalDetails;
import com.wegoing.service.NoticeService;
import com.wegoing.util.ClubUtil;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class NoticeController {
	private final NoticeService notieService;
	public NoticeController(NoticeService notieService) {
		this.notieService = notieService;
	}
	
	
	@GetMapping("/notice")
	public String noticeMain(@AuthenticationPrincipal PrincipalDetails userDetails,Model model) {
		List<NoticeDTO> notice = notieService.getNoticeList();
		
		model.addAttribute("myClub", ClubUtil.getClub(userDetails));
		model.addAttribute("notice",notice);
		return "notice/noticeMain";
	}
}
