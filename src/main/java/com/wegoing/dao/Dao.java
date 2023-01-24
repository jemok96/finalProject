package com.wegoing.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.wegoing.dto.MemberDTO;


@Repository
@Mapper
public interface Dao {
	List<MemberDTO> select();
}
