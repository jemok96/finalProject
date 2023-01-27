package com.wegoing.dao;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.wegoing.dto.BoardDTO;
import com.wegoing.service.BoardService;

import lombok.extern.slf4j.Slf4j;


@SpringBootTest
@Slf4j
class BoardTest {
	
	@Autowired
	BoardService boardService;

	// Test시 메서드 이름에 올려놓고 ctrl + F11해야 그것만 실행됨
	
	@BeforeEach
	void deleteAll() {
		boardService.deleteAll();
	}
	
	@Test
	void insertTest() {
		BoardDTO dto = BoardDTO.builder().email("rudnf9605@naver.com").cateno(4).bcontent("test").btitle("test").nickname("da").build();
		assertTrue(boardService.insert(dto) == 1);
		
	}
	@Test
	void insetTest2(){
		BoardDTO[] dto = new BoardDTO[100];
		for(int i=0; i<dto.length; i++) {
			dto[i] = BoardDTO.builder().email("rudnf9605@naver.com").cateno(4).bcontent("test").btitle("test").nickname("da").build();
			boardService.insert(dto[i]);
		}
		assertTrue(boardService.countBoard(4) == 100);
		assertTrue(boardService.deleteAll() == 100);
	}
	@Test
	void deleteCategoryTest() {
		BoardDTO dto =
				BoardDTO.builder().email("rudnf9605@naver.com").cateno(2).bcontent("test").btitle("test").nickname("da").build();
		boardService.insert(dto);
		assertTrue(boardService.deleteCategory(2) == 1);
	}

	@Test 
	void deleteOne() {
		assertTrue(boardService.deleteOne(4250) == 1);
		
	}

}
