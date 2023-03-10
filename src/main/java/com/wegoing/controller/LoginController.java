package com.wegoing.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wegoing.dto.MemberDTO;
import com.wegoing.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LoginController {

	private MemberService memberService;
	private PasswordEncoder passwordEncoder;
	
	public LoginController(MemberService memberService, PasswordEncoder passwordEncoder) {
		super();
		this.memberService = memberService;
		this.passwordEncoder = passwordEncoder;
	}

	@GetMapping("/register")
	public String registerForm(Model model) {
		return "home/register";
	}
	
	
	
	@PostMapping("/register") // insert
	public String registerOk(@ModelAttribute("dto")MemberDTO dto) {
		log.info("<<<<<<<<<<<<<<<<<<< postmapping");
		log.info("<<<<<<<<<<<<<<<<<<< email" + dto.getEmail());
		
		memberService.addOne(dto);
		return "redirect:/main";
	}
	
	@GetMapping("/login")
	public String login() {
		return "home/login";
	}
	
	@GetMapping("/findEmail")
	public String findEmail() {
		return "home/findEmail";
	}
	
	@PostMapping("/findEmail")
	@ResponseBody
	public String findEmailOk(@RequestParam("name") String name,
							  @RequestParam("tel") String tel) {
		log.info("name >>>" + name);
		log.info("tel >>>" + tel);
		
		MemberDTO mdto = MemberDTO.builder()
								  .name(name)
								  .tel(tel)
								  .build();
		String email = memberService.findEmail(mdto);
		return email;
	}
	
	@GetMapping("/resetPw")
	public String resetPw() {
		return "home/resetPw";
	}
	
	@PostMapping("/resetPw")
	public String resetPwOk(@RequestParam("checkPw")String checkPw,
							@RequestParam("email")String email) {
		log.info("email: " + email);
		log.info("pw: " + checkPw);
		String enPw = passwordEncoder.encode(checkPw);
		
		MemberDTO mdto = MemberDTO.builder()
								  .email(email)
								  .pw(enPw)
								  .build();
		memberService.updatePw(mdto);
		return "redirect:/main";
	}
}
