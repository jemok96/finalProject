package com.wegoing.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.wegoing.dao.BoardDao;
import com.wegoing.dto.BoardDTO;
import com.wegoing.service.BoardService;


@SpringBootTest
class BoardTest {
	
	@Autowired
	BoardService boardService;
	// Test시 메서드 이름에 올려놓고 ctrl + F11해야 그것만 실행됨
	@Test
	void insertTest() {
		BoardDTO dto =
		new BoardDTO("rudnf9605@naver.com", 2, "testTitle", "titleContent", "testNickname");
		System.out.println(dto);

		assertTrue(boardService.insert(dto) == 1);
	}
	@Test
	void deleteCategorySelect() {
		System.out.println(boardService.deleteCategoryNum(2));;
	}


}
