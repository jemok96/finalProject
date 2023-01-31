package com.wegoing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wegoing.dto.MemberDTO;
import com.wegoing.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@AllArgsConstructor
public class LoginController {

	private MemberService memberService;

	@GetMapping("/register")
	public String registerForm(Model model) {
		return "home/register";
	}
	
	@GetMapping("/testregister")
	public String registerTest() {
		return "home/testregister";
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
	
	@GetMapping("/logout")
	public String logout() {
		return "redirect:/";
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
}
