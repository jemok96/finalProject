package com.wegoing.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wegoing.dto.ClubDTO;
import com.wegoing.dto.PartnerDTO;
import com.wegoing.dto.PrincipalDetails;
import com.wegoing.service.ClubMemberService;
import com.wegoing.service.ClubService;
import com.wegoing.service.PartnerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {

	private ClubMemberService cms; 
	String loginEmail =""; 
	
	@Autowired
	public HomeController(ClubMemberService cmservice) {
		this.cms = cmservice; 
	}
	
	@GetMapping("/main")
	public String main(@AuthenticationPrincipal PrincipalDetails userDetails, Model model) {
		model.addAttribute("user", userDetails.getMdto());
		// 여기서부터 해당 아이디가 속한 협업공간 리스트를 뽑게 됩니다. 그래서 사이드바에 붙어있는 협업공간리스트에 보이게 하려고 합니다.
		// 그런데 이렇게 되면 매번 같은코드를 페이지 이동할때마다 보내야 합니다. 
		// 이 부분만 메서드로 뽑아내서 했을땐 제대로 동작되지않
		if( userDetails != null ) loginEmail = userDetails.getMdto().getEmail();
		List<ClubDTO> myClub= cms.selectAll(loginEmail);
		model.addAttribute("myClub", myClub); // 여기까지
		log.info("main돌아옴");
		return "home/mainpage";
	}
}
