package com.wegoing.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.wegoing.dto.ClubMemberDTO;

@Repository
@Mapper
public interface ClubMemberDAO {
	void insetClubMember(ClubMemberDTO dto);
}
