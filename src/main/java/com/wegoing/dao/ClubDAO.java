package com.wegoing.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.wegoing.dto.ClubDTO;

@Repository
@Mapper
public interface ClubDAO {
	List<ClubDTO> getAll();
	void createClub (ClubDTO dto);
	ClubDTO readOne(int clno);
	void updateOne(ClubDTO cdto);
	List<ClubDTO> selectAdminClub(String email, String crank);
	List<ClubDTO> findClub(String clname, String email);
}
