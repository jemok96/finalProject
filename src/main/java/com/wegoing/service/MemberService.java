package com.wegoing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wegoing.dao.Dao;
import com.wegoing.dto.MemberDTO;

@Service
public class MemberService {
	@Autowired
	Dao dao;
	public List<MemberDTO> select(){
		return dao.select();
	}
}
