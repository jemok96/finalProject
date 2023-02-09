package com.wegoing.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.wegoing.dto.NoticeDTO;
import com.wegoing.service.NoticeService;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class NoticeTest {
	@Autowired
	NoticeService noService;
	
	@Test
	void insertNotice() {
		NoticeDTO[] dt = new NoticeDTO[50000];
		for(int i=0; i <dt.length; i++) {
			dt[i]=NoticeDTO.builder().ntitle("test").ncontent("content").importance("normal").build();
			noService.writeNotice(dt[i]);
		}
		
	}
}
