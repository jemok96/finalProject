package com.wegoing.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
		session.setAttribute("nickname","제목스");
		session.setAttribute("email", "rudnf9605@naver.com");
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
	@GetMapping("/board/add")
	public String addboard(HttpServletRequest request,RedirectAttributes rattr) {
		HttpSession session = request.getSession(false);
		if(session == null) {
			rattr.addAttribute("msg","로그인해주세요");
			return "redirect:/wegoing/freeboard";
		}
		return "board/addboard";
	}
	@PostMapping("/board/add")
	public String insertBoard(@Validated @ModelAttribute("board")BoardDTO board,BindingResult bindingResult, HttpServletRequest request) {
		if(bindingResult.hasErrors()) {
			log.info("erros={}",bindingResult);
			return "board/addboard";
		}
		
		HttpSession session = request.getSession(false);
		
		board.setEmail((String) request.getAttribute("nickname"));
		board.setEmail((String)request.getAttribute("nickname"));
		log.info("board = {}",board);
		service.insert(board);
		return "redirect:/wegoing/board";
	}

	@GetMapping("/freeboard")
	public String freeboard(Model model) {
		List<BoardDTO> free = service.selectboard(2);
		model.addAttribute("free",free);
		return "board/freeboard";
	}
	@GetMapping("/freeboard/{bno}")
	public String freeboardDetail(@PathVariable int bno,Model model) {
		
		model.addAttribute("free",service.selectOne(bno));
		
		log.info("dto={}",service.selectOne(bno));
		return "board/freeboardDetail";
	}
	@GetMapping("/freeboard/{bno}/edit")
	public String freeboardEdit(@PathVariable int bno,Model model) {
		
		model.addAttribute("free",service.selectOne(bno));
		
		log.info("dto={}",service.selectOne(bno));
		return "board/freeboardEdit";
	}
	@PostMapping("/freeboard/{bno}/edit")
	public String freeboardEditcheck(@PathVariable int bno,Model model, @RequestParam("btitle")String btitle, @RequestParam("bcontent")String content) {
		BoardDTO free = service.selectOne(bno);
		model.addAttribute("free",free);
		
		free.setBtitle(btitle);
		free.setBcontent(content);
		log.info("free={}",free);
		service.update(free);
		
		return "redirect:/wegoing/freeboard";
	}
	@PostMapping("/freeboard/{bno}/delete")
	public String freeboardDelete(@PathVariable int bno) {
		log.info("bno={}",bno);
		return "redirect:/wegoing/board";
		
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
