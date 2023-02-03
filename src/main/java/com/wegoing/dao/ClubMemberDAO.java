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

	List<ClubDTO> getAll(String email);
}
