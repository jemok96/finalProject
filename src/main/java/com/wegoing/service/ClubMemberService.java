package com.wegoing.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wegoing.dao.ClubMemberDAO;
import com.wegoing.dto.ClubDTO;
import com.wegoing.dto.ClubMemberDTO;


@Service
public class ClubMemberService {
	private ClubMemberDAO cmDao;
	
	public ClubMemberService(ClubMemberDAO cmDao) {
		this.cmDao = cmDao;
	}
	
	public void addClubMember(ClubMemberDTO cmdto) {
		cmDao.insetClubMember(cmdto);
	}
	
	// 내가 속한 협업공간이름 구하기 
	public List<ClubDTO> selectAll(String email, String cstatus){		
		return cmDao.getAll(email, cstatus);
	}
	
	public List<ClubMemberDTO> selectMembers(int clno){
		return cmDao.getAllMembers(clno);
	}

	public ClubMemberDTO selectMemberByClnoAndEmail(int clno, String email) {
		return cmDao.getOneByClnoAndEmail(clno, email);
	}
	
	public List<String> getEmailByClno(int clno){
		return cmDao.selectEmailByClno(clno);
	}
	
	public ClubMemberDTO selectOne(ClubMemberDTO cmdto) {
		return cmDao.getOneMembers(cmdto);

	public void updateStatus(ClubMemberDTO cmdto) {
		cmDao.updateStatus(cmdto);
	}

	public void removeClubMember(long cno) {
		cmDao.deleteClubMember(cno);
	}

	public ClubMemberDTO getHost(int clno, String crank) {
		return cmDao.selectHost(clno, crank);
	}
}
