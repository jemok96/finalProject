package com.wegoing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wegoing.dao.ClubMemberDAO;
import com.wegoing.dto.ClubDTO;
import com.wegoing.dto.ClubMemberDTO;


@Service
public class ClubMemberService {
	
	private ClubMemberDAO cmDao;
	
	@Autowired
	public ClubMemberService(ClubMemberDAO dao ) {this.cmDao = dao;} 
	
	public void addClubMember(ClubMemberDTO cmdto) {
		cmDao.insetClubMember(cmdto);
	}
	
	// 내가 속한 협업공간이름 구하기 
	public List<ClubDTO> selectAll(String email){		
		return cmDao.getAll(email);
	}
	
	public List<ClubMemberDTO> selectMembers(int clno){
		return cmDao.getAllMembers(clno);
	}
}
