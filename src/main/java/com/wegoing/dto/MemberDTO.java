package com.wegoing.dto;


import com.mysql.cj.jdbc.Blob;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.wegoing.dto.ClubMemberDTO;


/*@Getter
@Setter*/
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDTO {
	private String email;
	private String name;
	private String nickname;
	private String pw;
	private String tel;
	private String image;
	private String auth;
	private String join_dt;
	
	private ClubMemberDTO cldto;
	private TodoDTO tododto;
	private ClubDTO clubdto;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public String getJoin_dt() {
		return join_dt;
	}

	public void setJoin_dt(String join_dt) {
		this.join_dt = join_dt;
	}

	public ClubMemberDTO getCldto() {
		return cldto;
	}

	public void setCldto(ClubMemberDTO cldto) {
		this.cldto = cldto;
	}

	public void setTododto(TodoDTO tododto) {
		this.tododto = tododto;
	}

	public TodoDTO getTododto() {
		return tododto;
	}

	public ClubDTO getClubdto() {
		return clubdto;
	}

	public void setClubdto(ClubDTO clubdto) {
		this.clubdto = clubdto;
	}
	
	
}
