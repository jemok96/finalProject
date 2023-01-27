package com.wegoing.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.wegoing.dto.MemberDTO;

@Repository
@Mapper
public interface MemberDAO {
	MemberDTO findMemberByEmail(String email); // email로 회원정보 조회
	void insertOne(MemberDTO dto); // 회원가입
	String findEmailByNameAndTel(Map<String, Object> Data); // 이메일 찾기
}
