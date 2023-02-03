package com.wegoing.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.wegoing.dto.PartnerDTO;

@Repository
@Mapper
public interface PartnerDAO {
	List<PartnerDTO> selectMyPartner(String email);
	int existsPartner(String pemail, String email);
	void insertOne(PartnerDTO pto);
	int enrollMe(String pemail, String email);
	void modifyPstatus(PartnerDTO pto);
	List<PartnerDTO> getRecPartner(String email, String pstatus);
	void deleteMyPartner(String email, String pemail);
}
