package com.wegoing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wegoing.dto.CommentDTO;
import com.wegoing.dto.PrincipalDetails;
import com.wegoing.service.CommentService;

import groovyjarjarpicocli.CommandLine.Model;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequestMapping("/comment")
public class CommentController {

   @Autowired
   CommentService mCommentService;
   
   
   @RequestMapping("/list") //댓글 리스트
   @ResponseBody
   private List<CommentDTO> mCommentServiceList(Model model,@RequestParam Integer bno){
       return mCommentService.commentList(bno);
   }
   
   @RequestMapping("/insert/{bno}") //댓글 작성 
   @ResponseBody
   private int mCommentServiceInsert(@PathVariable("bno") Integer bno, 
		   @RequestParam("cmcontent") String content,@AuthenticationPrincipal PrincipalDetails userDetails) throws Exception{

	   String writer = userDetails.getMdto().getNickname();
	   CommentDTO comment = new CommentDTO();
       comment.setBno(bno);
       comment.setCmcontent(content);
       comment.setCmwriter(writer);
       return mCommentService.commentInsert(comment);
   }
   
   @RequestMapping("/update/{cmno}") //댓글 수정  
   @ResponseBody
   private int mCommentServiceUpdateProc(@PathVariable Integer cmno, @RequestParam String cmcontent) throws Exception{
       
	   CommentDTO comment = new CommentDTO();
       comment.setCmno(cmno);
       comment.setCmcontent(cmcontent);

       return mCommentService.commentUpdate(comment);
   }
   
   @RequestMapping("/delete/{cno}") //댓글 삭제  
   @ResponseBody
   private int mCommentServiceDelete(@PathVariable int cno) throws Exception{
       log.info("");
       return mCommentService.commentDelete(cno);
   }
   
}