package com.wegoing.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wegoing.dao.CommentDao;
import com.wegoing.dto.CommentDTO;

@Service
public class CommentService {
	private final CommentDao dao;
	public CommentService(CommentDao dao) {
		this.dao = dao;
	}
	// 댓글 개수
    public int commentCount() {
    	return dao.commentCount();
    }
 
    // 댓글 목록
    public List<CommentDTO> commentList() {
    	return dao.commentList();
    }
 
    // 댓글 작성
    public int commentInsert(CommentDTO comment) {
    	return dao.commentInsert(comment);
    }
    
    // 댓글 수정
    public int commentUpdate(CommentDTO comment) {
    	return dao.commentUpdate(comment);
    }
 
    // 댓글 삭제
    public int commentDelete(int cno) {
    	return dao.commentDelete(cno);
    }
 
	
}
