package com.wegoing.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wegoing.dto.ClubDTO;
import com.wegoing.dto.MemberDTO;
import com.wegoing.dto.PartnerDTO;
import com.wegoing.dto.PrincipalDetails;
import com.wegoing.service.ClubMemberService;
import com.wegoing.service.MemberService;
import com.wegoing.service.PartnerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class PartnerController {
	
	private ClubMemberService cms;
	private MemberService ms;
	private PartnerService ps; 
	String loginEmail="";
	int result = 0; 
	
	public PartnerController(MemberService ms, PartnerService ps, ClubMemberService cmservice) {
		this.ms = ms;
		this.ps = ps; 
		this.cms = cmservice; 
	}
	
	@GetMapping("/partner")
	public String partnerForm(@AuthenticationPrincipal PrincipalDetails userDetails, Model model) {

		// 여기도 동일한 코드가 필요
		if( userDetails != null ) loginEmail = userDetails.getMdto().getEmail();
		List<ClubDTO> myClub= cms.selectAll(loginEmail);
		model.addAttribute("myClub", myClub);
		String pstatus = "n"; 
		List<PartnerDTO> pto =  ps.selectRecPartner(loginEmail, pstatus);
		model.addAttribute("recPartner", pto);
		List<MemberDTO> mto = ms.getMyPartners(loginEmail);
		model.addAttribute("myPartner", mto);
		return "partner/partnerForm";
	}
	
	@PostMapping("partner/partnerCK")
	@ResponseBody
	public int searchPartner(@RequestParam("partnerEmail")String partnerEmail,@AuthenticationPrincipal PrincipalDetails userDetails) {
		if( userDetails != null ) loginEmail = userDetails.getMdto().getEmail();
		log.info(loginEmail);
		log.info(partnerEmail);
		if( partnerEmail.equals(loginEmail) ) { // 자기자신 이메일을 넣었을 시 
			return 4; 
		}
		System.out.println("여기");
		int cnt = ms.emailCheck(partnerEmail); // 상대방 이메일이 회원가입되어있는지  
		if ( cnt == 1) { 			
			result = ps.isPartner(partnerEmail, loginEmail); // 내가 이미 파트너 추가했는지
			log.info("ok");
			if (result == 0 ) {
				log.info("파트너가능");
				return 1; 
			} else {
				log.info("이미 내가 파트너로 상대방 등록");
				return 2; 
			}
		}
		log.info("회원도 아님");
		return 3; 
	}
	
	@PostMapping("partner/partnerForm")
	public String addPartner(@RequestParam("partnerEmail")String partnerEmail,@AuthenticationPrincipal PrincipalDetails userDetails,
			@ModelAttribute("pto")PartnerDTO pto) {
		if( userDetails != null ) loginEmail = userDetails.getMdto().getEmail();
		// 상대방이 나를 파트너로 등록했는지 여부 보기 
		result = ps.registerMe(partnerEmail, loginEmail);
		pto.setEmail(loginEmail);
		pto.setPemail(partnerEmail);
		if ( result == 1 ) {
			log.info("상대방은 나를 파트너로 등록함");
			// 상대방이 나를 파트너로 등록한 데이터에서 pstatus n-> y로 업데이트
			pto.setPstatus("y");
			ps.updatePstatus(pto);
			log.info("상태 y로 업뎃");
			// 내꺼 insert, pstatus 'y'  
			ps.enrollPartner(pto);
		}else {
			log.info("내가 먼저 등록");
			// 내꺼 insert, pstatus 'n'
			pto.setPstatus("n");
			ps.enrollPartner(pto);
		}

		return "redirect:/partner";
	}
	
	@PostMapping("partner/deletePartner")
	@ResponseBody
	public String deletePartner(@RequestParam("deleteEmail")String partnerEmail,@AuthenticationPrincipal PrincipalDetails userDetails, @ModelAttribute("pto")PartnerDTO pto) {
		if( userDetails != null ) loginEmail = userDetails.getMdto().getEmail();
		log.info(loginEmail);
		log.info(partnerEmail);
		ps.erasePartner(loginEmail, partnerEmail);
		pto.setEmail(loginEmail);
		pto.setPemail(partnerEmail);
		pto.setPstatus("n");
		ps.updatePstatus(pto);
		return "redirect:/partner";
	}

}
