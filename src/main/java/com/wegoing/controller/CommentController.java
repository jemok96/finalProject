package com.wegoing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wegoing.dto.CommentDTO;
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
       log.info("list bno ={}",bno);
       return mCommentService.commentList(bno);
   }
   
   @RequestMapping("/insert") //댓글 작성 
   @ResponseBody
   private int mCommentServiceInsert(@RequestParam("bno") Integer bno, @RequestParam("cmcontent") String content) throws Exception{
       log.info("{}",bno);
	   CommentDTO comment = new CommentDTO();
       comment.setBno(bno);
       comment.setCmcontent(content);
       //로그인 기능을 구현했거나 따로 댓글 작성자를 입력받는 폼이 있다면 입력 받아온 값으로 사용하면 됩니다. 저는 따로 폼을 구현하지 않았기때문에 임시로 "test"라는 값을 입력해놨습니다.
       comment.setCmwriter("test");  
       
       return mCommentService.commentInsert(comment);
   }
   
   @RequestMapping("/update") //댓글 수정  
   @ResponseBody
   private int mCommentServiceUpdateProc(@RequestParam int cno, @RequestParam String content) throws Exception{
       
	   CommentDTO comment = new CommentDTO();
       comment.setBno(cno);
       comment.setCmcontent(content);
       
       return mCommentService.commentUpdate(comment);
   }
   
   @RequestMapping("/delete/{cno}") //댓글 삭제  
   @ResponseBody
   private int mCommentServiceDelete(@PathVariable int cno) throws Exception{
       
       return mCommentService.commentDelete(cno);
   }
   
}