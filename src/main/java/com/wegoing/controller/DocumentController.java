package com.wegoing.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nimbusds.jose.shaded.json.JSONArray;
import com.wegoing.dto.ClubDTO;
import com.wegoing.dto.ClubMemberDTO;
import com.wegoing.dto.DocumentDTO;
import com.wegoing.dto.MemberDTO;
import com.wegoing.dto.PrincipalDetails;
import com.wegoing.dto.TodoDTO;
import com.wegoing.enumpackage.Dstatus;
import com.wegoing.service.ClubMemberService;
import com.wegoing.service.ClubService;
import com.wegoing.service.DocumentService;
import com.wegoing.service.MemberService;
import com.wegoing.service.TodoService;
import com.wegoing.util.ClubUtil;
import com.wegoing.util.PageUtil;

import lombok.extern.slf4j.Slf4j;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

@Controller
@Slf4j
@RequestMapping("/club")
public class DocumentController {

	@Autowired
	DocumentService service;
	
	@Autowired
	ClubService clubService;
	
	@Autowired
	MemberService memberservice;
	
	@Autowired
	TodoService todoservice;
	
	@Autowired
	ClubMemberService clubmemberservice;

	// 협업 공간 디테일?
	@GetMapping({"/{clno}/document/list", "/{clno}"})
	public String clubDocumentlist(@PathVariable("clno") int clno, Model model,
			@RequestParam(name = "cp", defaultValue = "1") int currentPage,
			@AuthenticationPrincipal PrincipalDetails userDetails){
		
		int totalNumber = service.getTotal(clno);

		Map<String, Object> map = PageUtil.getPageData(totalNumber, currentPage);
		int startNo = (int) map.get("startNo");
		int endNo = (int) map.get("endNo");
		log.info(">>>>>>>>>>>>>>>>>>list");
		// 한페이지당 7개씩 보이는 메서드
		List<DocumentDTO> list = service.getAll(clno, startNo);
		
		List<MemberDTO> cmlist = memberservice.getMembersInfo(clno);
		String email = userDetails.getMdto().getEmail();
		ClubMemberDTO clubmemdto = ClubMemberDTO.builder()
												.clno(clno)
												.email(email)
												.build();
		ClubMemberDTO cmdto = clubmemberservice.selectOne(clubmemdto);
//		MemberDTO dto = memberservice.getMembersOne(clno, email);
		
//		log.info("<<<<<<<<<<<<<<dto"+dto);
		

		model.addAttribute("list", list);
		model.addAttribute("map", map);
		model.addAttribute("myClub", ClubUtil.getClub(userDetails));
		ClubDTO cdto = clubService.getOne(clno);
		model.addAttribute("club", cdto);
		model.addAttribute("cmlist", cmlist);
		model.addAttribute("cmdto", cmdto);
		

		return "club/document/dlist";
	}

	// 디테일 화면
	@RequestMapping("/{clno}/document/dtail")
	public String dtail(@RequestParam("dno") int dno, @PathVariable("clno") int clno, Model model
						,@AuthenticationPrincipal PrincipalDetails userDetails) {
//		log.info("<<<<<<<<<<<<<<<<dtail");
		DocumentDTO dto = service.getOne(dno);
//		log.info("<<<<<<<<<<<<<<<<dtail" + dto);
		
		List<MemberDTO> todoList = todoservice.selectdno(dno);
		ClubDTO cdto = clubService.getOne(clno);
		
		
		model.addAttribute("dto", dto);
		model.addAttribute("myClub", ClubUtil.getClub(userDetails));
		model.addAttribute("club", cdto);
		model.addAttribute("todoList", todoList);

		return "club/document/ddtail";
	}

	@GetMapping("/{clno}/document/modify")
	public String modifyForm(@RequestParam("dno") int dno, Model model
							,@PathVariable("clno") int clno
							,@AuthenticationPrincipal PrincipalDetails userDetails) {
		DocumentDTO dto = service.getOne(dno);
		List<MemberDTO> todoList = todoservice.selectdno(dno);
		ClubDTO cdto = clubService.getOne(clno);
		String email = userDetails.getMdto().getEmail();
		List<MemberDTO> cmlist = memberservice.getMembersInfo(clno);
		ClubMemberDTO clubmemdto = ClubMemberDTO.builder()
											    .clno(clno)
											    .email(email)
											    .build();
		ClubMemberDTO cmdto = clubmemberservice.selectOne(clubmemdto);

		
		model.addAttribute("dto", dto);
		model.addAttribute("myClub", ClubUtil.getClub(userDetails));
		model.addAttribute("club", cdto);
		model.addAttribute("todoList", todoList);
		model.addAttribute("cmdto", cmdto);
		model.addAttribute("cmlist", cmlist);

		return "club/document/modifyForm";

	}

	@PostMapping("/{clno}/document/modify")
	public String modifyOk(@ModelAttribute("dto") DocumentDTO dto2, Model model,
			RedirectAttributes redirectAttributes) {

		// 수정 메서드
		service.modify(dto2);
		int dno = dto2.getDno();

		return "redirect:/club/" + dto2.getClno() + "/document/dtail?dno=" + dno;

	}

	@GetMapping("/{clno}/document/delete")
	public String deleteOne(@RequestParam("dno") int dno, @PathVariable("clno") int clno) {
		todoservice.removeDno(dno);
		service.remove(dno);
		
		

		return "redirect:/club/" + clno + "/document/list";
	}

	

	@PostMapping("/{clno}/document/write")
	public String writeForm(@PathVariable("clno")int clno,
							@ModelAttribute("dto")DocumentDTO dto,
							@RequestParam("ajson")String ajson) {
		dto.setClno(clno);
		service.write(dto);
		
		
		log.info(ajson);
		int dno = dto.getDno();
		log.info("<<<<<<<<<<<<"+dno);
		todoservice.write(dno,ajson);

		
		
		
		
//		log.info("<<<<<<<<<<<<<<<<<"+ja);
		
		
		return "redirect:/club/" + clno + "/document/list";
	}
	//칸반 dstatus update
	@PostMapping("document/updateDstatus") 
	@ResponseBody
	public void updateDstatus(@RequestParam("dropzone")String dstatus, @RequestParam("dno") int dno) {
		
		if (dstatus.equals("start")) {
			dstatus ="발의됨";
		} else if ( dstatus.equals("ongoing")) {
			dstatus = "진행중";
		} else if ( dstatus.equals("stop")) {
			dstatus = "일시중지";
		} else if ( dstatus.equals("done")) {
			dstatus ="완료";
		} else {
			log.info("이벤트 감지 이상");
		}
		
		service.modify(dstatus, dno);
	}
	
}
