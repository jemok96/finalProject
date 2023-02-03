package com.wegoing.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wegoing.dto.ClubDTO;
import com.wegoing.dto.DocumentDTO;
import com.wegoing.service.ClubService;
import com.wegoing.service.DocumentService;
import com.wegoing.util.PageUtil;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/club")
public class DocumentController {

	@Autowired
	DocumentService service;
	
	@Autowired
	ClubService clubService;

	// 협업 공간 디테일?
	@GetMapping("/{clno}/document/list")
	public String clubDocumentlist(@PathVariable("clno") int clno, Model model,
			@RequestParam(name = "cp", defaultValue = "1") int currentPage) {

		int totalNumber = service.getTotal(clno);

		Map<String, Object> map = PageUtil.getPageData(totalNumber, currentPage);
		int startNo = (int) map.get("startNo");
		int endNo = (int) map.get("endNo");
		log.info(">>>>>>>>>>>>>>>>>>list");
		// 한페이지당 7개씩 보이는 메서드
		List<DocumentDTO> list = service.getAll(clno, startNo);

		model.addAttribute("list", list);
		model.addAttribute("map", map);
		
		ClubDTO cdto = clubService.getOne(clno);
		model.addAttribute("club", cdto);

		return "club/document/dlist";
	}

	// 디테일 화면
	@RequestMapping("/{clno}/document/dtail")
	public String dtail(@RequestParam("dno") int dno, @PathVariable("clno") int clno, Model model) {
		log.info("<<<<<<<<<<<<<<<<dtail");
		DocumentDTO dto = service.getOne(dno);
		log.info("<<<<<<<<<<<<<<<<dtail" + dto);
		model.addAttribute("dto", dto);
		
		ClubDTO cdto = clubService.getOne(clno);
		model.addAttribute("club", cdto);

		return "club/document/ddtail";
	}

	@GetMapping("/{clno}/document/modify")
	public String modifyForm(@RequestParam("dno") int dno, Model model
							,@PathVariable("clno") int clno) {
		DocumentDTO dto = service.getOne(dno);

		model.addAttribute("dto", dto);
		
		ClubDTO cdto = clubService.getOne(clno);
		model.addAttribute("club", cdto);

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
		service.remove(dno);

		return "redirect:/club/" + clno + "/document/list";
	}

	
	@GetMapping("/{clno}/document/write")
	public String writeForm(@PathVariable("clno")int clno) {
//		service.write(clno);
		
		return "club/document/writeForm";
	}
	 

}
