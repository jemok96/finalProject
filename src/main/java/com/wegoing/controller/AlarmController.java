package com.wegoing.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.wegoing.dto.AlarmDTO;
import com.wegoing.dto.PrincipalDetails;
import com.wegoing.service.AlarmService;
import com.wegoing.util.ClubUtil;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AlarmController {
	private final AlarmService alarmService;

	@GetMapping(value="/subscribe", produces = "text/event-stream")
	@ResponseBody
	public SseEmitter subscribe(@AuthenticationPrincipal PrincipalDetails userDetails,
								@RequestHeader(value = "Last-Event-ID", required = false, defaultValue = "") 
								String lastEventId) {
		String userId = userDetails.getMdto().getEmail();
		return alarmService.subscribe(userId, lastEventId);
	}
	
	// 알람 조회 
	@GetMapping("/alarmList")
	public String alarmList(@AuthenticationPrincipal PrincipalDetails userDetails, Model model) {
		model.addAttribute("user", userDetails.getMdto());
		model.addAttribute("myClub", ClubUtil.getClub(userDetails));
		return "home/alarm";
	}
    
	// 알람 삭제
    @PostMapping("/alarm/remove")
    public String removeAlarm(@RequestParam("ano") long ano) {
    	alarmService.removeAlarm(ano);
    	return "redirect:/alarmList";
    }
}
