package com.wegoing.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wegoing.dto.BoardDTO;
import com.wegoing.dto.NoticeDTO;
import com.wegoing.dto.PageHandler;
import com.wegoing.dto.PrincipalDetails;
import com.wegoing.service.MemberService;
import com.wegoing.service.NoticeService;
import com.wegoing.util.ClubUtil;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class NoticeController {
	private final NoticeService notieService;
	private final MemberService memService;
	public NoticeController(NoticeService notieService,MemberService memService) {
		this.notieService = notieService;
		this.memService = memService;
	}
	
	
	@GetMapping("/notice")
	public String noticeMain(@AuthenticationPrincipal PrincipalDetails userDetails,Model model,
			@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer pageSize) {
	
		String auth = ClubUtil.getAuth(userDetails);
		int count = notieService.getNoticeCount();
		
		int totalCnt = notieService.getNoticeCount();
		PageHandler pageHandler = new PageHandler(totalCnt, page, pageSize);

		Map<String, Integer> map = new HashMap();
		map.put("offset", (page - 1) * pageSize);
		map.put("pageSize", pageSize);

		List<NoticeDTO> notice = notieService.getNoticeList(map);
		

		model.addAttribute("ph", pageHandler);
		model.addAttribute("page", page);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("count",count);
		model.addAttribute("myClub", ClubUtil.getClub(userDetails));
		model.addAttribute("notice",notice);
		model.addAttribute("auth",auth);
		return "notice/noticeMain";
	}
}
