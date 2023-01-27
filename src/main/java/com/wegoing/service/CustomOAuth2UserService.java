package com.wegoing.service;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.wegoing.dao.MemberDAO;
import com.wegoing.dto.MemberDTO;
import com.wegoing.dto.PrincipalUserDetails;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
	@Autowired
	MemberDAO mdao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
		public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
			OAuth2User oAuth2User = super.loadUser(userRequest);
			
			Map<String, Object> userMap
				= (Map<String, Object>) oAuth2User.getAttributes().get("response"); // 소셜로그인 유저의 정보를 map에 담음

			String email = (String) userMap.get("email");
			String name = (String) userMap.get("name");
			String nickname = (String) userMap.get("nickname");
			String uuid = UUID.randomUUID().toString().substring(0, 6);
			String password = passwordEncoder.encode("password"+uuid);
			String mobile = (String) userMap.get("mobile");
			String auth = "ROLE_USER";
			
			MemberDTO mdto = mdao.findMemberByEmail(email);
			log.info("dto : " + mdto);
			if(mdto == null) {
				mdto = MemberDTO.builder()
							   .email(email)
							   .name(name)
							   .nickname(nickname)
							   .pw(password)
							   .tel(mobile)
							   .auth(auth)
							   .build();
				mdao.insertOne(mdto);
			}

			log.info("dto : " + mdto);
			log.info("user : " + userMap);
			return new PrincipalUserDetails(mdto, userMap);			
		}
}
