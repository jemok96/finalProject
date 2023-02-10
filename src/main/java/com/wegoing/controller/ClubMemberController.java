package com.wegoing.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wegoing.dto.ClubDTO;
import com.wegoing.dto.ClubMemberDTO;
import com.wegoing.dto.MemberDTO;
import com.wegoing.dto.PrincipalDetails;
import com.wegoing.enumpackage.AlarmType;
import com.wegoing.enumpackage.ClubMemberStatus;
import com.wegoing.enumpackage.ClubRank;
import com.wegoing.service.AlarmService;
import com.wegoing.service.ClubMemberService;
import com.wegoing.service.ClubService;
import com.wegoing.service.MemberService;
import com.wegoing.util.ClubUtil;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@AllArgsConstructor
public class ClubMemberController {
	private ClubService cs;
	private ClubMemberService cms; 
	private MemberService ms;
	private AlarmService as;
	
	@GetMapping("/club/{clno}/member")
	public String clubMember(@PathVariable int clno, Model model,
							@AuthenticationPrincipal PrincipalDetails userDetails) {
		model.addAttribute("user", userDetails.getMdto());
		model.addAttribute("myClub", ClubUtil.getClub(userDetails));
		
		ClubDTO cdto = cs.getOne(clno);
		String loginEmail = userDetails.getMdto().getEmail();
		log.info("Email >>>>> " + loginEmail);
		
		List<MemberDTO> partner = ms.getMyPartners(loginEmail);
		List<MemberDTO> member = ms.getMembersInfo(clno);
		List<String> memEmail = cms.getEmailByClno(clno);
		ClubMemberDTO hostInfo = cms.getHost(clno, ClubRank.host.crank());

		model.addAttribute("club", cdto);
		model.addAttribute("partnerList", partner);		
		model.addAttribute("memberList", member);
		model.addAttribute("emailList", memEmail);
		model.addAttribute("hostInfo", hostInfo);
		
		
		return "club/clubMember";
	}
	
	@PostMapping("/addClubMember")
	@ResponseBody
	public String addClubMember(@RequestParam("clno") int clno,
								@RequestParam("email") String email) {
		log.info("추가 이메일 >>>>> " + email);
		ClubDTO cdto = cs.getOne(clno);
		ClubMemberDTO cmdto = ClubMemberDTO.builder()
										   .clno(clno)
										   .crank(ClubRank.guest.crank())
										   .cstatus("n")
										   .email(email)
										   .build();
		cms.addClubMember(cmdto);
		
		String url = "/club/" + cdto.getClno();
		String content = "[" + cdto.getClname() + "] 로부터 초대를 받았습니다.";
		as.send(email, AlarmType.clubInvitaion, content, url);
		return "redirect:/club/{clno}/member";
	}

	@PostMapping("/acceptInvitation")
	public String acceptInvitation(@RequestParam("ano") long ano,
							   @RequestParam("clno") int clno,
							   @RequestParam("email") String email) {
		ClubMemberDTO cmdto = cms.selectMemberByClnoAndEmail(clno, email);
		cmdto.setCstatus(ClubMemberStatus.accept.cstatus());						   
		cms.updateStatus(cmdto);
		
		as.readAlarm(ano);
		return "redirect:/alarmList";
	}
	
	@PostMapping("/rejectInvitation")
	public String rejectInvitation(@RequestParam("ano") long ano,
							   @RequestParam("clno") int clno,
							   @RequestParam("email") String email) {
		ClubMemberDTO cmdto = cms.selectMemberByClnoAndEmail(clno, email);
		long cno = cmdto.getCno();
		
		as.readAlarm(ano);
		cms.removeClubMember(cno);
		return "redirect:/alarmList";
	}
	
	@PostMapping("/deleteClubMember")
	public String removeClubMember(@RequestParam("cno") long cno) {
		cms.removeClubMember(cno);
		return "redirect:/";
	}
	
	
}
