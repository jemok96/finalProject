package com.wegoing.dao;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.wegoing.dto.BoardDTO;
import com.wegoing.dto.CommentDTO;
import com.wegoing.service.BoardService;
import com.wegoing.service.CommentService;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class CommentTest {

	@Autowired
	BoardService boardService;
	@Autowired
	CommentService commentService;
	

	@Test 
	void insertTest() {
		BoardDTO boardDTO = BoardDTO.
				builder().btitle("안녕").bcontent("하이").cateno(4).nickname("JM").build();
		assertTrue(boardService.insert(boardDTO) == 1);
		BoardDTO boardDTO2 = boardService.selectTest(boardDTO.getCateno());

		CommentDTO commentDTO = 
				CommentDTO.builder().
				cmwriter(boardDTO2.getNickname()).
				cmcontent("좋은 글입니다~~").bno(boardDTO2.getBno()).build();

		assertTrue(commentService.commentInsert(commentDTO) == 1);
		
		
	}
}
