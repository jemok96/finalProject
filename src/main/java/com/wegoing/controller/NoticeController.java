package com.wegoing.controller;

import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wegoing.dto.BoardDTO;
import com.wegoing.dto.NoticeDTO;
import com.wegoing.dto.PageHandler;
import com.wegoing.dto.PrincipalDetails;
import com.wegoing.enumpackage.Message;
import com.wegoing.service.MemberService;
import com.wegoing.service.NoticeService;
import com.wegoing.util.ClubUtil;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class NoticeController {
	private final NoticeService notieService;
	private final MemberService memService;
	public NoticeController(NoticeService notieService,MemberService memService) {
		this.notieService = notieService;
		this.memService = memService;
	}
	
	
	@GetMapping("/notice")
	public String noticeMain(@AuthenticationPrincipal PrincipalDetails userDetails,Model model,
			@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer pageSize) {
	
		String auth = ClubUtil.getAuth(userDetails);
		int count = notieService.getNoticeCount();
		
		int totalCnt = notieService.getNoticeCount();
		PageHandler pageHandler = new PageHandler(totalCnt, page, pageSize);

		Map<String, Integer> map = new HashMap();
		map.put("offset", (page - 1) * pageSize);
		map.put("pageSize", pageSize);

		List<NoticeDTO> notice = notieService.getNoticeList(map);
		

		model.addAttribute("ph", pageHandler);
		model.addAttribute("page", page);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("count",count);
		model.addAttribute("myClub", ClubUtil.getClub(userDetails));
		model.addAttribute("notice",notice);
		model.addAttribute("auth",auth);
		
		return "notice/noticeMain";
	}
	@GetMapping("/notice/{notino}")
	public String noticeDetail(@PathVariable Integer notino,Model model
			,HttpServletRequest request, HttpServletResponse response,@AuthenticationPrincipal PrincipalDetails userDetails) throws Exception {
		
		String auth = ClubUtil.getAuth(userDetails);
		NoticeDTO notice = notieService.getNoticeOne(notino);
		updateHitCookie(notino,request,response);
		 
		model.addAttribute("auth",auth);
		model.addAttribute("notice",notice);
		model.addAttribute("notino",notino);
		
		return "notice/noticeDetail";
		
	}
	@GetMapping("/notice/{notino}/edit")
	public String noticeEdit(@PathVariable Integer notino,Model model) throws Exception {
		NoticeDTO notice = notieService.getNoticeOne(notino);
		
		model.addAttribute("notice",notice);
		
		return "notice/noticeEdit";
	}
	@PostMapping("/notice/{notino}/edit")
	public String noticeEditCheck(@PathVariable Integer notino,Model model, @Validated @ModelAttribute("notice") NoticeDTO noticeM
			,BindingResult bindingResult) throws Exception {
		if(bindingResult.hasErrors()) {
			log.info("erros={}", bindingResult);
			return "notice/noticeEdit";
		}
		NoticeDTO notice = notieService.getNoticeOne(notino);
		
		model.addAttribute("notice",notice);
		Date date = new Date();

		
		noticeM.setUp_dt(date);
		noticeM.setNcontent(noticeM.getNcontent());
		noticeM.setNtitle(noticeM.getNtitle());
	
		
		notieService.noticeUpdate(noticeM);
		return "redirect:/notice";
		
	}
	@PostMapping("/notice/{notino}/delete")
	public String noticeDelete(@PathVariable Integer notino,RedirectAttributes rattr) {
		notieService.noticeDelete(notino);
		rattr.addFlashAttribute(Message.SUCCESS.message(),Message.SUCMSG.message());
		
		return "redirect:/notice";
		
	}
	
	
	
	
	
	
	private void updateHitCookie(Integer notino,HttpServletRequest request, HttpServletResponse response) {
		Cookie oldCookie = null;
		 Cookie[] cookies = request.getCookies();
		    if (cookies != null) {
		        for (Cookie cookie : cookies) {
		           if (cookie.getName().equals("postView")) {
		                oldCookie = cookie;
		           }
		        }
		    }
		    if (oldCookie != null) {
		        if (!oldCookie.getValue().contains("["+ notino.toString() +"]")) {
		            notieService.updateHits(notino);
		            oldCookie.setValue(oldCookie.getValue() + "_[" + notino + "]");
		            oldCookie.setPath("/");
		            oldCookie.setMaxAge(60 * 60 * 12);
		            response.addCookie(oldCookie);
		        }
		    } else {
		    	notieService.updateHits(notino);
		        Cookie newCookie = new Cookie("postView", "[" + notino + "]");
		        newCookie.setPath("/");
		        newCookie.setMaxAge(60 * 60 * 12);
		        response.addCookie(newCookie);
		       
		    }
	}
}
