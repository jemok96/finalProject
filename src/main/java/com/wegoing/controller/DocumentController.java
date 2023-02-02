package com.wegoing.controller;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.wegoing.dto.DocumentDTO;
import com.wegoing.service.DocumentService;
import com.wegoing.util.PageUtil;

import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
@RequestMapping("/document")
public class DocumentController {

	@Autowired
	DocumentService service;
	

	// 협업 공간 디테일?
	@GetMapping("/list")
	public String clubDocumentlist(@RequestParam("clno")int clno,Model model, @RequestParam(name = "cp", defaultValue = "1")int currentPage) {
		
		int totalNumber = service.getTotal(clno);
		
		
		// 												  총페이지수 ,	  한페이지당 수, 	현재페이지 몇페이지
		Map<String, Object> map = PageUtil.getPageData(totalNumber, currentPage);
		int startNo = (int)map.get("startNo");
		int endNo = (int)map.get("endNo");
		
//		log.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<GetMapping"+clno);
		
		// 한페이지당 7개씩 보이는 메서드
		List<DocumentDTO> list = service.getAll(clno,startNo);
		
		model.addAttribute("clno", clno);
		model.addAttribute("list", list);
		model.addAttribute("map", map);
		
		
		return "club/document/dlist";
	}
	
	// 디테일 화면
	@RequestMapping("/dtail")
	public String dtail(@RequestParam("dno")int dno,
						@RequestParam("clno")int clno,
						Model model) {
		log.info("<<<<<<<<<<<<<<<<dtail");
		DocumentDTO dto = service.getOne(dno);
		log.info("<<<<<<<<<<<<<<<<dtail" + dto);
		model.addAttribute("dto", dto);
		model.addAttribute("clno", clno);
		
		return "club/document/ddtail";
	}
	
	@GetMapping("/modify")
	public String modifyForm(@RequestParam("dno")int dno,Model model) {
		DocumentDTO dto = service.getOne(dno);
		
		model.addAttribute("dto", dto);
		
		return "club/document/modifyForm";
		
	}
	
	@PostMapping("/modify")
	public String modifyOk(@ModelAttribute("dto")DocumentDTO dto2,Model model) {
		
		// 수정 메서드
		service.modify(dto2);
		
		DocumentDTO dto = service.getOne(dto2.getDno());
		model.addAttribute("dto", dto);
		
		model.addAttribute("clno", dto.getClno());
		
		return "club/document/ddtail";
		
	}

	
}
