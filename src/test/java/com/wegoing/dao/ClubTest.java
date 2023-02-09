package com.wegoing.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.wegoing.service.ClubService;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class ClubTest {

	@Autowired
	ClubService service;
	
	@Test
	void updateClubTest() {
		
	}

}
