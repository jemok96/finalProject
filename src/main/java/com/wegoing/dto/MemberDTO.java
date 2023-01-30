package com.wegoing.dto;


import com.mysql.cj.jdbc.Blob;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDTO {
	private String email;
	private String name;
	private String nickname;
	private String pw;
	private String tel;
	private Blob image;
	private String auth;
	private String join_dt;

}
