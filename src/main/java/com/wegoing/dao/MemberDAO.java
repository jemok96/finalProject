package com.wegoing.dao;

import java.util.List;

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
	List<MemberDTO> selectMyPartners(String email); // 내파트너 조회하기, 이미지도 불러와야해서 MemberDAO이용

	List<MemberDTO> selectClubMembersInfo(int clno); 
	String getMemberAuth(String email);
}
