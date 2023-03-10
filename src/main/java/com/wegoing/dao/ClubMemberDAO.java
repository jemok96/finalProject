package com.wegoing.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.wegoing.dto.ClubDTO;
import com.wegoing.dto.ClubMemberDTO;

@Repository
@Mapper
public interface ClubMemberDAO {
	void insetClubMember(ClubMemberDTO dto);

	List<ClubDTO> getAll(String email, String cstatus);
	List<ClubMemberDTO> getAllMembers(int clno);
	ClubMemberDTO getOneByClnoAndEmail(long clno, String email);
	List<String> selectEmailByClno(int clno);
	ClubMemberDTO getOneMembers(ClubMemberDTO cmdto);
	void updateStatus(ClubMemberDTO cmdto);
	void deleteClubMember(long cno);
	ClubMemberDTO selectHost(long clno, String crank);
}
