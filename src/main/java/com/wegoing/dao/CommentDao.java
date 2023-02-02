
package com.wegoing.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.wegoing.dto.CommentDTO;

@Repository
@Mapper
public interface CommentDao {
	    // 댓글 개수
	    public int commentCount(int bno);
	 
	    // 댓글 목록
	    public List<CommentDTO> commentList(int bno) ;
	 
	    // 댓글 한개만
	    public CommentDTO commentOne(int cmno);
	    // 댓글 작성
	    public int commentInsert(CommentDTO comment);
	    
	    // 댓글 수정
	    public int commentUpdate(CommentDTO comment);
	 
	    // 댓글 삭제
	    public int commentDelete(int cno);
	    // 게시판 관련 댓글 전부 삭제
	    public int commentDeleteBno(int bno);
	
}
