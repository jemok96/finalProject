package com.wegoing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wegoing.dao.ClubDAO;
import com.wegoing.dto.ClubDTO;

import lombok.NoArgsConstructor;

@Service
public class ClubService {
	
	private ClubDAO clubDao;

	@Autowired
	public ClubService(ClubDAO dao) {this.clubDao = dao;} 
	
	public void addClub(ClubDTO cdto) {
		clubDao.createClub(cdto);
	}
	

}
