package com.wegoing.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

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
	
	public ClubDTO getOne(int clno) {
		return clubDao.readOne(clno);
	}
	
	public void modifyOne(ClubDTO cdto) {
		clubDao.updateOne(cdto);
	}

	public List<ClubDTO> getAdminClub(String email, String crank) {
		return clubDao.selectAdminClub(email, crank);
	}
	
	public List<ClubDTO> searchKeyword(String searchValue, String email) {
		List<ClubDTO> clubDtoList = clubDao.findClub(searchValue ,email);
		return clubDtoList;
	}
}
