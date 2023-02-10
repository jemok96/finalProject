package com.wegoing.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.wegoing.dto.MemberDTO;
import com.wegoing.dto.PrincipalDetails;
import com.wegoing.service.MemberService;
import com.wegoing.util.ClubUtil;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MemberController {
	
	private MemberService memberService;
	private PasswordEncoder passwordEncoder;
	
	private final String uploadPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\img\\";
	
	public MemberController(MemberService memberService, PasswordEncoder passwordEncoder) {
		super();
		this.memberService = memberService;
		this.passwordEncoder = passwordEncoder;
	}

	@PostMapping("/member/emailCheck")
	@ResponseBody
	public int emailCheck(@RequestParam("email")String email) {
		log.info("<<<<<<<<<<<<<<<< emailCheck()" + email);
		int cnt = memberService.emailCheck(email);
		log.info("<<<<<<<<<<<<<<<< cnt : "+cnt);
//		cnt =1;
		return cnt;
	}
	
	@PostMapping("/member/nicknameCheck")
	@ResponseBody
	public int nicknameCheck(@RequestParam("nickname")String nickname) {
		log.info("<<<<<<<<<<<<<<<< nicknameCheck()" + nickname);
		int cnt = memberService.nicknameCheck(nickname);
		log.info("<<<<<<<<<<<<<<<< cnt : "+cnt);
		
		return cnt;
	}
	
	@GetMapping("/mypage")
	public String mypageAuth(@AuthenticationPrincipal PrincipalDetails userDetails, Model model) {
		model.addAttribute("user", userDetails.getMdto());
		model.addAttribute("myClub", ClubUtil.getClub(userDetails));
		
		return "home/mypageAuth";
	}
	
	@PostMapping("/mypage/password")
	@ResponseBody
	public String mypagePwCheck(@RequestParam("password") String password,
								@AuthenticationPrincipal PrincipalDetails userDetails) {
		String userPw = userDetails.getMdto().getPw();
		
		if(passwordEncoder.matches(password, userPw)) {
			return "correspond";
		}else {
			return "differ";
		}
	}
	
	@PostMapping("/mypage/edit")
	public String mypageEdit(@AuthenticationPrincipal PrincipalDetails userDetails, Model model) {
		String email = userDetails.getMdto().getEmail();
		MemberDTO user  = memberService.findMemberByEmail(email);
		model.addAttribute("user", user);
		model.addAttribute("myClub", ClubUtil.getClub(userDetails));
		return "/home/mypage";
	}
	
	@PostMapping("/mypage/editOk")
	public String mypageEditOk(@AuthenticationPrincipal PrincipalDetails userDetails,
								@ModelAttribute("Member") MemberDTO member, 
								@RequestParam("file") MultipartFile file) throws IOException {
		String filename = "";
		String savefileName = "";
		
		if (!file.isEmpty()) {
            filename = file.getOriginalFilename();
            savefileName = UUID.randomUUID().toString() + "_" + filename;
            File saveFile = new File(uploadPath, savefileName);
            file.transferTo(saveFile);
        }
		member.setImage(savefileName);
		
		String encodePw = passwordEncoder.encode(member.getPw());
		member.setPw(encodePw);
		
		userDetails.setMdto(member);
		
		memberService.editOne(member);
		return "redirect:/main";
	}

}
