package com.wegoing.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.wegoing.dto.MemberDTO;
import com.wegoing.dto.PrincipalUserDetails;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class PrincipalUserDetails implements UserDetails, OAuth2User{

	private MemberDTO mdto;
	private Map<String, Object> attribute;
	
	// 일반 로그인 사용자
	public PrincipalUserDetails(MemberDTO mdto) {
		this.mdto = mdto;
	}
	
	// 소셜 로그인 사용자
	public PrincipalUserDetails(MemberDTO mdto, Map<String, Object> attribute) {
		this.mdto = mdto;
		this.attribute = attribute;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> auth = new ArrayList<>();
		auth.add(new GrantedAuthority() {
			@Override
			public String getAuthority() {
				return mdto.getAuth();
			}
		});
		return auth;
	}

	@Override
	public String getPassword() {
		return mdto.getPw();
	}

	@Override
	public String getUsername() {
		return mdto.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public Map<String, Object> getAttributes() {
		return attribute;
	}

	@Override
	public String getName() {
		return attribute.get("sub").toString();
	}
}
