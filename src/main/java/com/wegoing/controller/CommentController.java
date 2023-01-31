package com.wegoing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wegoing.dto.CommentDTO;
import com.wegoing.dto.PrincipalDetails;
import com.wegoing.service.CommentService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequestMapping("/comment")
public class CommentController {

   @Autowired
   CommentService commentService;

   @PostMapping("/list/{bno}")
   public String getCommentList(Model model,@PathVariable Integer bno,
		   @AuthenticationPrincipal PrincipalDetails userDetails) {
	   String nickname = "";
	   if(userDetails != null) nickname = userDetails.getMdto().getNickname();
	   model.addAttribute("comments",commentService.commentList(bno));
	   model.addAttribute("nickname",nickname);
       return "commentList";
   
   }
   @PostMapping("/insert/{bno}")
   public String insertComment(Model model,@RequestParam String cmcontent,
		   @AuthenticationPrincipal PrincipalDetails userDetails,@PathVariable Integer bno) {
	   log.info("{}",cmcontent);
	   String writer = userDetails.getMdto().getNickname();
	   CommentDTO com = new CommentDTO();
	   com.setCmcontent(cmcontent);
	   com.setCmwriter(writer);
	   com.setBno(bno);
	   int result = commentService.commentInsert(com);
	   log.info("result ={}",result);
       return "commentList";
   
   }
   @PostMapping("/delete/{cmno}")
   public String insertComment(Model model,
		   @AuthenticationPrincipal PrincipalDetails userDetails,@PathVariable Integer cmno) {
	   log.info("{}",cmno);
	   CommentDTO com = new CommentDTO();

	   int result = commentService.commentDelete(cmno);
	   log.info("result ={}",result);
       return "commentList";
   
   }
}