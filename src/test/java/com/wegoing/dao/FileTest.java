package com.wegoing.dao;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.wegoing.dto.FileDTO;
import com.wegoing.service.FileService;

import lombok.extern.slf4j.Slf4j;


@SpringBootTest
@Slf4j
public class FileTest {
	@Autowired
	FileService fileService;
	
	@Test
	void getFileListTest() {
		FileDTO dto = FileDTO.builder().fname("ddd.png").
				fpath("adasdasdas").
				clno(1).build();
		assertTrue(fileService.uploadFile(dto)==1);
	}
	@Test 
	void deleteFile(){
		assertTrue(fileService.deleteFile(2)==1);
	}
}
