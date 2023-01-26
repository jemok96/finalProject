package com.wegoing.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wegoing.dto.BoardDTO;
import com.wegoing.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@AllArgsConstructor
@Slf4j
@RequestMapping("/wegoing")
public class BoardController {
	private final BoardService service;
	
	
	
	@GetMapping("/board")
	public String boardMain(Model model, HttpSession session) {
		session.setAttribute("nickName","제목스");
		/**
		 * 1번 취미 2번 블라블라  3번 반려동물  4번 커리어
		 */
		List<BoardDTO> hobby = service.selectCategory(1);
		List<BoardDTO> free = service.selectCategory(2);
		List<BoardDTO> pet = service.selectCategory(3);
		List<BoardDTO> career = service.selectCategory(4);
		log.info("pet ={}",pet);
		model.addAttribute("hobby",hobby);
		model.addAttribute("free",free);
		model.addAttribute("pet",pet);
		model.addAttribute("career",career);
		return "board/boardmain";
	}

	// 이거 존나 중복되는데 
	@GetMapping("/freeboard")
	public String freeboardBno(Model model) {
		List<BoardDTO> free = service.selectboard(2);
		model.addAttribute("free",free);
		return "board/freeboard";
	}
	@GetMapping("/freeboard/{bno}")
	public String freeboardBno(@PathVariable int bno,Model model) {
		log.info("bno={}",bno);
		return "board/freeboard";
	}
	
	@GetMapping("/petboard")
	public String petboard(Model model) {
		List<BoardDTO> pet = service.selectboard(3);
		model.addAttribute("pet",pet);
		return "board/freeboard";
	}
	@GetMapping("/petboard/{bno}")
	public String petboardBno(@PathVariable int bno,Model model) {
		log.info("bno={}",bno);
		return "board/freeboard";
	}
	
	@GetMapping("/career")
	public String career(Model model) {
		List<BoardDTO> career = service.selectboard(4);
		model.addAttribute("career",career);
		return "board/freeboard";
	}
	@GetMapping("/career/{bno}")
	public String careerboardBno(@PathVariable int bno,Model model) {
		log.info("bno={}",bno);
		return "board/freeboard";
	}
	
	@GetMapping("/hobby")
	public String hobby(Model model) {
		List<BoardDTO> hobby = service.selectboard(1);
		model.addAttribute("hobby",hobby);
		return "board/freeboard";
	}
	@GetMapping("/hobby/{bno}")
	public String hobbyBoardBno(@PathVariable int bno,Model model) {
		log.info("bno={}",bno);
		return "board/freeboard";
	}
	
	
	
	
	
	@GetMapping("/boardbasic")
	public String basic() {
		return "board/boardbasic";
	}
}
