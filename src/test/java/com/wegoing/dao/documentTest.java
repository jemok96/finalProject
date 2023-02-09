package com.wegoing.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.wegoing.dto.DocumentDTO;
import com.wegoing.service.DocumentService;

import lombok.extern.slf4j.Slf4j;
@SpringBootTest
@Slf4j
class documentTest {
	
	@Autowired
	DocumentService service;

	@Test
	void insertTest() {
		DocumentDTO dto = DocumentDTO.builder()
									 .dname("안녕하세요")
									 .dcontent("에러 짜증나용")
									 .dstatus("진행중")
									 .dwriter("비비비")
									 .clno(39)
									 .build();
		
		service.write(dto);
		log.info("Dno" + dto.getDno()); 
	}

}
