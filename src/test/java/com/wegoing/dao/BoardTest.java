package com.wegoing.dao;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.wegoing.dto.BoardDTO;
import com.wegoing.dto.PageHandler;
import com.wegoing.dto.SearchCondition;
import com.wegoing.service.BoardService;

import lombok.extern.slf4j.Slf4j;


@SpringBootTest
@Slf4j
class BoardTest {
	
	@Autowired
	BoardService boardService;
	
//	@BeforeEach
//	void deleteAll() {
//		boardService.deleteAll();
//	}
//	
	@Test
	void insertTest() {
		
		BoardDTO dto = BoardDTO.builder().email("aaa@gmail.com").cateno(4).bcontent("test").btitle("test").nickname("da").build();
		assertTrue(boardService.insert(dto) == 1);
		
	}
	@Test
	void insetTest2(){
	
		BoardDTO[] dto3 = new BoardDTO[3000];
		BoardDTO[] dto4 = new BoardDTO[3000];

		for(int i=0; i<dto3.length; i++) {
			dto3[i] = BoardDTO.builder().email("aaa@gmail.com").cateno(3).bcontent("test").btitle("test").nickname("da").build();
			boardService.insert(dto3[i]);
		}for(int i=0; i<dto4.length; i++) {
			dto4[i] = BoardDTO.builder().email("aaa@gmail.com").cateno(4).bcontent("test").btitle("test").nickname("da").build();
			boardService.insert(dto4[i]);
		}
	}
	@Test
	void deleteCategoryTest() {
		BoardDTO dto =
				BoardDTO.builder().email("aaa@gmail.com").cateno(2).bcontent("test").btitle("test").nickname("da").build();
		boardService.insert(dto);
		assertTrue(boardService.deleteCategory(2) == 1);
	}

	@Test 
	void deleteOne() {
		assertTrue(boardService.deleteOne(4250) == 1);
		
	}
	@Test
    void PageHandlertest(){
        PageHandler ph = new PageHandler(255,11);
        log.info("11/10*10+1 = {}" , 11/10*10+1);
        ph.print();

       assertTrue(ph.getBeginPage() ==11);
       assertTrue(ph.getEndPage() ==20);
    }

	@Test
	void serachSelectPageTest() {
		boardService.deleteCategory(3);
		for(int i=1; i<=20; i++) {
			BoardDTO boardDTO = BoardDTO.builder().btitle("title"+i).bcontent("asdsa").cateno(3).email("aaa@gmail.com")
					.nickname("JM").build();
			boardService.insert(boardDTO);
		}
		SearchCondition sc = new SearchCondition(1,10,"T","title2",3);
		System.out.println(sc.getOffset()+""+sc.getPage());
		List<BoardDTO> list = boardService.searchSelectPage(sc);
		System.out.println("list = "+list);
		assertTrue(list.size() ==2 );
	}
	@Test
	void serachResultCntTest() {
		boardService.deleteCategory(3);
		for(int i=1; i<=20; i++) {
			BoardDTO boardDTO = BoardDTO.builder().btitle("title"+i).bcontent("asdsa").cateno(3).email("aaa@gmail.com")
					.nickname("JM").build();
			boardService.insert(boardDTO);
		}
		SearchCondition sc = new SearchCondition(1,10,"T","title2",3);
		
		int result = boardService.searchResultCnt(sc);
		
		assertTrue(result ==2 );
	}
	
}
