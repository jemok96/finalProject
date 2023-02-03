package com.wegoing.config;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.wegoing.dto.MemberDTO;
import com.wegoing.service.MemberService;

@SpringBootTest
class SecurityConfigTest {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private MemberService memberService;
	
	
	@Test
	void testInsert() {
		MemberDTO dto = MemberDTO.builder()
								 .email("ccc@naver.com")
								 .name("비비비")
								 .nickname("비비빅")
								 .pw("ccc")
								 .tel("010-2222-6565")
								 .build();
		
		
		String enPw = passwordEncoder.encode(dto.getPw());
		
		System.out.println(" password : " + enPw);
		
		boolean matchResult = passwordEncoder.matches(dto.getPw(), enPw);
		dto.setPw(enPw);
				
		
		memberService.addOne(dto);
		
		assertTrue(matchResult);
		
		
	}

}
