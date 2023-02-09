package com.wegoing.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wegoing.dto.BoardDTO;
import com.wegoing.dto.PageHandler;
import com.wegoing.dto.PrincipalDetails;
import com.wegoing.enumpackage.Category;
import com.wegoing.enumpackage.Message;
import com.wegoing.service.BoardService;
import com.wegoing.service.CommentService;
import com.wegoing.util.ClubUtil;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j

public class BoardController {
	private final BoardService service;
	private final CommentService commentService;

	public BoardController(BoardService service, CommentService commentService) {
		this.service = service;
		this.commentService = commentService;
	}

	@GetMapping("/board")
	public String boardMain(Model model, @AuthenticationPrincipal PrincipalDetails userDetails) {
		List<BoardDTO> hobby = service.selectCategory(Category.hobby.cateno());
		List<BoardDTO> free = service.selectCategory(Category.free.cateno());
		List<BoardDTO> pet = service.selectCategory(Category.pet.cateno());
		List<BoardDTO> career = service.selectCategory(Category.career.cateno());
		model.addAttribute("hobby", hobby);
		model.addAttribute("free", free);
		model.addAttribute("pet", pet);
		model.addAttribute("career", career);
		model.addAttribute("myClub", ClubUtil.getClub(userDetails));
		
		return "board/boardmain";
	}

	@GetMapping("/board/add")
	public String addboard(@AuthenticationPrincipal PrincipalDetails userDetails, RedirectAttributes rattr,
			Model model) {

		if (userDetails == null) {
			rattr.addFlashAttribute("msg", Message.MSG.message());
			return "redirect:/freeboard";
		}
		model.addAttribute("board", new BoardDTO());
		model.addAttribute("myClub", ClubUtil.getClub(userDetails));
		return "board/addboard";
	}

	@PostMapping("/board/add")
	public String insertBoard(@Validated @ModelAttribute("board") BoardDTO board, BindingResult bindingResult,
			@AuthenticationPrincipal PrincipalDetails userDetails) {
		
		if (bindingResult.hasErrors()) {
			log.info("erros={}", bindingResult);
			return "board/addboard";
		}
		if (userDetails == null) {
			return "error-page/500";
		}
		board.setEmail(userDetails.getMdto().getEmail());
		board.setNickname(userDetails.getMdto().getNickname());
		service.insert(board);
		return "redirect:/board";
	}

	@GetMapping("/freeboard")
	public String freeboard(Model model, @RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer pageSize,
			@AuthenticationPrincipal PrincipalDetails userDetails) {

		PageHandlerModel(model, page, pageSize, Category.free.cateno());
		model.addAttribute("myClub", ClubUtil.getClub(userDetails));
		return "board/free/freeboard";
	}

	@GetMapping("/freeboard/{bno}")
	public String freeboardDetail(@PathVariable Integer bno, Model model,
			@AuthenticationPrincipal PrincipalDetails userDetails, HttpServletRequest request,
			HttpServletResponse response) {

		BoardDTO free = service.selectOne(bno); // 게시물 작성자
		if (free == null) {
			return "error-page/500";
		}

		// 접속자와 댓글쓴사람 닉네임이 같으면 수정삭제가능
		String nickname = "";
		String writer = free.getNickname(); // 게시물 작성자 닉네임
		service.updateHit(bno);

		if (userDetails != null)
			nickname = userDetails.getMdto().getNickname();
		if (!nickname.equals("") && nickname.equals(writer)) { // 작성자와 접속자가 같으면
			model.addAttribute(Message.NICK.message(), Message.YES.message());
		} else {
			model.addAttribute(Message.NICK.message(), Message.NO.message());
		}
		model.addAttribute("comCount", commentService.commentCount(bno));

		model.addAttribute("free", free);
		model.addAttribute("myClub", ClubUtil.getClub(userDetails));
		return "board/free/detailBoard";
	}

	@GetMapping("/freeboard/{bno}/edit")
	public String freeboardEdit(@PathVariable int bno, Model model,
			@AuthenticationPrincipal PrincipalDetails userDetails) {
		BoardDTO free = service.selectOne(bno);
		if (free == null) {
			return "error-page/500";
		}
		model.addAttribute("board", service.selectOne(bno));
		model.addAttribute("myClub", ClubUtil.getClub(userDetails));
		return "board/free/editBoard";
	}

	@PostMapping("/freeboard/{bno}/edit")
	public String freeboardEditcheck(@Validated @ModelAttribute("board") BoardDTO board, BindingResult bindingResult,
			@PathVariable int bno, Model model) {
		if (bindingResult.hasErrors()) {
			log.info("erros={}", bindingResult);
			return "board/free/editBoard";
		}
		BoardDTO free = service.selectOne(bno);
		setContentsEdit(bno,board,free);

		service.update(free);
		model.addAttribute("board", free);
		return "redirect:/freeboard";
	}

	@PostMapping("/freeboard/{bno}/delete")
	public String freeboardDelete(@PathVariable int bno, RedirectAttributes ratt) {
		commentService.commentDeleteBno(bno);
		service.deleteOne(bno);
		ratt.addFlashAttribute("success", "삭제 되었습니다");
		return "redirect:/freeboard";
	}

	@GetMapping("/pet")
	public String petboard(Model model, @RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer pageSize,
			@AuthenticationPrincipal PrincipalDetails userDetails) {
		PageHandlerModel(model, page, pageSize, Category.pet.cateno());
		model.addAttribute("myClub", ClubUtil.getClub(userDetails));
		return "board/pet/petBoard";
	}

	@GetMapping("/pet/{bno}")
	public String petboardDetail(@PathVariable Integer bno, Model model,
			@AuthenticationPrincipal PrincipalDetails userDetails) {

		BoardDTO free = service.selectOne(bno);
		if (free == null) {
			return "error-page/500";
		}
		String nickname = null;
		String writer = free.getNickname();
		service.updateHit(bno);

		if (userDetails != null)
			nickname = userDetails.getMdto().getNickname();
		if (nickname != null && nickname.equals(writer)) {
			model.addAttribute(Message.NICK.message(), Message.YES.message());
		} else {
			model.addAttribute(Message.NICK.message(), Message.NO.message());
		}
		model.addAttribute("comCount", commentService.commentCount(bno));
		model.addAttribute("free", free);
		model.addAttribute("myClub", ClubUtil.getClub(userDetails));
		return "board/pet/detailBoard";
	}

	@GetMapping("/pet/{bno}/edit")
	public String petboardEdit(@PathVariable int bno, Model model,
			@AuthenticationPrincipal PrincipalDetails userDetails) {
		BoardDTO free = service.selectOne(bno);
		if (free == null) {
			return "error-page/500";
		}

		model.addAttribute("board", service.selectOne(bno));
		model.addAttribute("myClub", ClubUtil.getClub(userDetails));
		return "board/pet/editBoard";
	}

	@PostMapping("/pet/{bno}/edit")
	public String petboardEditcheck(@Validated @ModelAttribute("board") BoardDTO board, BindingResult bindingResult,
			@PathVariable int bno, Model model) {
		if (bindingResult.hasErrors()) {
			log.info("erros={}", bindingResult);
			return "board/pet/editBoard";
		}
		BoardDTO free = service.selectOne(bno);
		setContentsEdit(bno, board, free);
		service.update(free);
		model.addAttribute("board", free);
		return "redirect:/pet";
	}

	@PostMapping("/pet/{bno}/delete")
	public String petboardDelete(@PathVariable int bno, RedirectAttributes ratt) {
		commentService.commentDeleteBno(bno);
		service.deleteOne(bno);
		ratt.addFlashAttribute("success", "삭제 되었습니다");
		return "redirect:/pet";
	}
//	================================================

	@GetMapping("/hobby")
	public String hobbyBoard(Model model, @RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer pageSize,
			@AuthenticationPrincipal PrincipalDetails userDetails) {
		PageHandlerModel(model, page, pageSize, Category.hobby.cateno());
		model.addAttribute("myClub", ClubUtil.getClub(userDetails));
		return "board/hobby/hobbyBoard";
	}

	@GetMapping("/hobby/{bno}")
	public String hobbyboardDetail(@PathVariable Integer bno, Model model,
			@AuthenticationPrincipal PrincipalDetails userDetails) {

		BoardDTO free = service.selectOne(bno);
		if (free == null) {
			return "error-page/500";
		}
		String nickname = null;
		String writer = free.getNickname();
		service.updateHit(bno);

		if (userDetails != null)
			nickname = userDetails.getMdto().getNickname();
		if (nickname != null && nickname.equals(writer)) {
			model.addAttribute(Message.NICK.message(), Message.YES.message());
		} else {
			model.addAttribute(Message.NICK.message(), Message.NO.message());
		}
		model.addAttribute("comCount", commentService.commentCount(bno));
		model.addAttribute("free", free);
		model.addAttribute("myClub", ClubUtil.getClub(userDetails));
		return "board/hobby/detailBoard";
	}

	@GetMapping("/hobby/{bno}/edit")
	public String hobbyboardEdit(@PathVariable int bno, Model model,
			@AuthenticationPrincipal PrincipalDetails userDetails) {
		BoardDTO free = service.selectOne(bno);
		if (free == null) {
			return "error-page/500";
		}

		model.addAttribute("board", service.selectOne(bno));
		model.addAttribute("myClub", ClubUtil.getClub(userDetails));
		return "board/hobby/editBoard";
	}

	@PostMapping("/hobby/{bno}/edit")
	public String hobbyboardEditcheck(@Validated @ModelAttribute("board") BoardDTO board, BindingResult bindingResult,
			@PathVariable int bno, Model model) {
		if (bindingResult.hasErrors()) {
			log.info("erros={}", bindingResult);
			return "board/hobby/editBoard";
		}

		BoardDTO free = service.selectOne(bno);
		setContentsEdit(bno, board, free);

		service.update(free);
		model.addAttribute("board", free);
		return "redirect:/hobby";
	}

	@PostMapping("/hobby/{bno}/delete")
	public String hobbyboardDelete(@PathVariable int bno, RedirectAttributes ratt) {
		commentService.commentDeleteBno(bno);
		service.deleteOne(bno);
		ratt.addFlashAttribute("success", "삭제 되었습니다");
		return "redirect:/hobby";
	}

	@GetMapping("/career")
	public String careerBoard(Model model, @RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer pageSize,
			@AuthenticationPrincipal PrincipalDetails userDetails) {
		PageHandlerModel(model, page, pageSize, Category.career.cateno());
		model.addAttribute("myClub", ClubUtil.getClub(userDetails));
		return "board/career/careerBoard";
	}

	@GetMapping("/career/{bno}")
	public String careerboardDetail(@PathVariable Integer bno, Model model,
			@AuthenticationPrincipal PrincipalDetails userDetails) {

		BoardDTO free = service.selectOne(bno);
		if (free == null) {
			return "error-page/500";
		}
		String nickname = "";
		String writer = free.getNickname();
		service.updateHit(bno);

		if (userDetails != null)
			nickname = userDetails.getMdto().getNickname();// 접속자 닉네임
		if (!nickname.equals("") && nickname.equals(writer)) {
			model.addAttribute(Message.NICK.message(), Message.YES.message());
		} else {
			model.addAttribute(Message.NICK.message(), Message.NO.message());
		}
		model.addAttribute("comCount", commentService.commentCount(bno));
		model.addAttribute("free", free);
		model.addAttribute("bno", bno);
		model.addAttribute("nickname", nickname);
		model.addAttribute("myClub", ClubUtil.getClub(userDetails));
		return "board/career/detailBoard";
	}

	@GetMapping("/career/{bno}/edit")
	public String careerboardEdit(@PathVariable int bno, Model model,
			@AuthenticationPrincipal PrincipalDetails userDetails) {
		BoardDTO free = service.selectOne(bno);
		if (free == null) {
			return "error-page/500";
		}
		
		model.addAttribute("board", service.selectOne(bno));
		model.addAttribute("myClub", ClubUtil.getClub(userDetails));
		return "board/career/editBoard";
	}

	@PostMapping("/career/{bno}/edit")
	public String careerboardEditcheck(@Validated @ModelAttribute("board") BoardDTO board, BindingResult bindingResult,
			@PathVariable int bno, Model model) {

		if (bindingResult.hasErrors()) {
			log.info("erros={}", bindingResult);
			return "board/career/editBoard";
		}

		BoardDTO free = service.selectOne(bno);
		setContentsEdit(bno, board, free);

		service.update(free);
		model.addAttribute("board", free);
		return "redirect:/career";

	}

	@PostMapping("/career/{bno}/delete")
	public String careerboardDelete(@PathVariable int bno, RedirectAttributes ratt) {
		commentService.commentDeleteBno(bno);
		service.deleteOne(bno);
		ratt.addFlashAttribute("success", "삭제 되었습니다");
		return "redirect:/career";
	}

	
	
	
//=============================
	private void setContentsEdit(int bno, BoardDTO board, BoardDTO free) {

		Date date = new Date();

		free.setBtitle(board.getBtitle());
		free.setBcontent(board.getBcontent());
		free.setBno(board.getBno());
		free.setUp_dt(date);

	}

	private Model PageHandlerModel(Model model, @RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer pageSize, Integer cateno) {

		int totalCnt = service.countBoard(cateno);
		PageHandler pageHandler = new PageHandler(totalCnt, page, pageSize);

		Map<String, Integer> map = pageHandler(page, pageSize, cateno);

		List<BoardDTO> free = service.selectPage(map);
		List<Integer> comCount = new ArrayList<Integer>();

		for (int i = 0; i < free.size(); i++) {
			comCount.add(commentService.commentCount(free.get(i).getBno()));
		}

		model.addAttribute("ph", pageHandler);
		model.addAttribute("page", page);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("free", free);
		model.addAttribute("comCount", comCount);
		return model;
	}

	private Map<String, Integer> pageHandler(Integer page, Integer pageSize, Integer cate) {
		Map<String, Integer> map = new HashMap();
		map.put("offset", (page - 1) * pageSize);
		map.put("pageSize", pageSize);
		map.put("bno", cate);
		return map;
	}
}
