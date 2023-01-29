package com.wegoing.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.wegoing.dto.ClubDTO;

@Repository
@Mapper
public interface ClubDAO {
	void createClub (ClubDTO dto);
}
