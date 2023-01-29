package com.wegoing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wegoing.dao.ClubMemberDAO;
import com.wegoing.dto.ClubMemberDTO;


@Service
public class ClubMemberService {
	
	private ClubMemberDAO cmDao;
	
	@Autowired
	public ClubMemberService(ClubMemberDAO dao ) {this.cmDao = dao;} 
	
	public void addClubMember(ClubMemberDTO cmdto) {
		cmDao.insetClubMember(cmdto);
	}
}
