package com.wegoing.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.wegoing.service.CustomOAuth2UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final CustomOAuth2UserService customOAuth2UserService;

	@Bean
	static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeHttpRequests()
		.antMatchers("/", "/login").permitAll()
		.antMatchers("/main").authenticated()
		.anyRequest().permitAll()
		.and()
		.formLogin().loginPage("/login")
		.defaultSuccessUrl("/main")
		.failureUrl("/login?error")
		.and()
		.logout()
		.logoutUrl("/logout")
		.logoutSuccessUrl("/")
		.invalidateHttpSession(true).deleteCookies("JSESSION")
		.and()
		.oauth2Login().loginPage("/login")
		.defaultSuccessUrl("/main")
		.userInfoEndpoint()
		.userService(customOAuth2UserService);
	}
}
