package com.wegoing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.wegoing.dao.MemberDAO;
import com.wegoing.dto.MemberDTO;

@Service
public class MemberService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	MemberDAO dao;
	
	// email 중복 찾기
	public int emailCheck(String email) {
		MemberDTO dto = dao.findMemberByEmail(email);
		int confirm;
		if(dto != null) {
			confirm = 1;
		}else {
			confirm = 0;
		}
		return confirm;
	}
	
	// 닉네임 중복 찾기
	public int nicknameCheck(String nickname) {
		MemberDTO dto = dao.findNickNameCheck(nickname);
		int confirm;
		if(dto != null) {
			confirm = 1;
		}else {
			confirm = 0;
		}
		return confirm;
	}
	
	public void addOne(MemberDTO dto) {
		String enPw = passwordEncoder.encode(dto.getPw());
		
		dto.setPw(enPw);
		
		dao.insertOne(dto);
	}
	
	public String findEmail(MemberDTO dto) {
		return dao.findEmailByNameAndTel(dto);
	}
	
	public void updatePw(MemberDTO dto) {
		dao.updatePw(dto);
	}
	
}
