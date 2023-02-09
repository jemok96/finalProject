package com.wegoing.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wegoing.dto.PrincipalDetails;
import com.wegoing.enumpackage.Dstatus;
import com.wegoing.service.ClubService;
import com.wegoing.service.DocumentService;
import com.wegoing.util.ClubUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {

//	@Resource(name="clubUtil")
//	private clubUtil clubUtil; 
	private final DocumentService ds; 
	private final ClubService cs;
	
	public HomeController(DocumentService ds, ClubService cs) {
		this.ds = ds; 
		this.cs = cs;
	}
	
	
	@GetMapping("/main")
	public String main(@AuthenticationPrincipal PrincipalDetails userDetails, Model model) {
		model.addAttribute("user", userDetails.getMdto());
		model.addAttribute("myClub", ClubUtil.getClub(userDetails)); 
		String crank = "host";
		model.addAttribute("adminClub", cs.getAdminClub(userDetails.getMdto().getEmail(), crank));
		return "home/mainpage";
	}
	
	@PostMapping("home/updateChart") 	
	public @ResponseBody List<Integer> updateChart(@RequestParam("clno")int clno) {
		
		List<Integer> dstausChart = new ArrayList<>();
		// 협업공간 이름으로 협업공간 문서에 있는 상태 
		int start = ds.getDstatusCount(Dstatus.start.dstatus(), clno);		
		int ongoing = ds.getDstatusCount(Dstatus.ongoing.dstatus(), clno);
		int stop = ds.getDstatusCount(Dstatus.stop.dstatus(), clno);
		int done = ds.getDstatusCount(Dstatus.done.dstatus(), clno);
		int sum = start + ongoing + stop + done; 
		
		dstausChart.add(getPercent(start, sum));
		dstausChart.add(getPercent(ongoing, sum));
		dstausChart.add(getPercent(stop, sum));
		dstausChart.add(getPercent(done, sum));
		
		return dstausChart; 
        
	}
	
	public int getPercent(int value, int sum ) { // 차트용
		double i = (double)value/sum;
		int result = (int) Math.round((i*100));
		return result; 
	}
}
