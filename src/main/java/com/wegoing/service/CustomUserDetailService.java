package com.wegoing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.wegoing.dao.MemberDAO;
import com.wegoing.dto.MemberDTO;
import com.wegoing.dto.PrincipalDetails;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomUserDetailService implements UserDetailsService{
	@Autowired
	MemberDAO mdao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberDTO mdto = mdao.findMemberByEmail(username);
		log.info("mdto: " + mdto);
		if(mdto == null) {
			throw new UsernameNotFoundException("user not authorized");
		}
		return new PrincipalDetails(mdto);
	}

}
