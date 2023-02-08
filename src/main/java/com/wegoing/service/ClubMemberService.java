package com.wegoing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.wegoing.dao.ClubMemberDAO;
import com.wegoing.dto.AddClubMemberEvent;
import com.wegoing.dto.ClubDTO;
import com.wegoing.dto.ClubMemberDTO;
import com.wegoing.enumpackage.ClubRank;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;


@Service
public class ClubMemberService {
	private ClubMemberDAO cmDao;
	private ApplicationEventPublisher eventPublisher;
	
	public ClubMemberService(ClubMemberDAO cmDao, ApplicationEventPublisher eventPublisher) {
		this.cmDao = cmDao;
		this.eventPublisher = eventPublisher;
	}
	
	public void addClubMember(ClubMemberDTO cmdto, ClubDTO cdto) {
		cmDao.insetClubMember(cmdto);

		if(cmdto.getCrank().equals(ClubRank.guest.crank())) { // 초대받은 사용자라면 알람이 가도록 함
			eventPublisher.publishEvent(new AddClubMemberEvent(cmdto, cdto));
		}
	}
	
	// 내가 속한 협업공간이름 구하기 
	public List<ClubDTO> selectAll(String email){		
		return cmDao.getAll(email);
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
	
	public void updateStatus(ClubMemberDTO cmdto) {
		cmDao.updateStatus(cmdto);
	}

	public void removeClubMember(long cno) {
		cmDao.deleteClubMember(cno);
	}
}
