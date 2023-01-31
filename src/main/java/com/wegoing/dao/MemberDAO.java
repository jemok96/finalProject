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
	MemberDTO findNickNameCheck(String nickname);
	String findEmailByNameAndTel(MemberDTO dto); // 이메일 찾기
	void updatePw(MemberDTO dto); // 비밀번호 재설정
}
