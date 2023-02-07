package com.wegoing.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.wegoing.dto.MemberDTO;

@Repository
@Mapper
public interface NotificationDAO {
	long countByEmailAndChecked();

	long countByAccount(MemberDTO account);
}
