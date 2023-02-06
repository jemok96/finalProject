package com.wegoing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wegoing.dto.BoardDTO;
import com.wegoing.dto.ClubDTO;
import com.wegoing.dto.DocumentDTO;
import com.wegoing.dto.PrincipalDetails;
import com.wegoing.enumpackage.Category;
import com.wegoing.enumpackage.Dstatus;
import com.wegoing.service.ClubService;
import com.wegoing.service.KanbanService;
import com.wegoing.util.ClubUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class KanbanController {

	private final ClubService clubService;
	
	private final KanbanService kanbanService; 
	
	public KanbanController (ClubService clubService, KanbanService kanbanService) {
		this.clubService = clubService; 
		this.kanbanService = kanbanService;
	}
	
	@GetMapping("club/{clno}/kanban")
	public String kanban(@AuthenticationPrincipal PrincipalDetails userDetails,@PathVariable("clno") int clno, Model model) {
		ClubDTO cdto = clubService.getOne(clno);
		model.addAttribute("club", cdto);

		// dstatus에 따라 dno, dname가져오기 
		List<DocumentDTO> start = kanbanService.getDstatusList(Dstatus.start.dstatus(), clno);
		List<DocumentDTO> ongoing = kanbanService.getDstatusList(Dstatus.ongoing.dstatus(), clno);
		List<DocumentDTO> stop = kanbanService.getDstatusList(Dstatus.stop.dstatus(), clno);
		List<DocumentDTO> done = kanbanService.getDstatusList(Dstatus.done.dstatus(), clno);
		
		model.addAttribute("start", start);
		model.addAttribute("ongoing", ongoing);
		model.addAttribute("stop", stop);
		model.addAttribute("done", done);
		model.addAttribute("myClub", ClubUtil.getClub(userDetails)); 
		
		return "kanban/kanban";
	}

}
