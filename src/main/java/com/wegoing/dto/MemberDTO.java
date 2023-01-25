package com.wegoing.dto;


import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
	private String email;
	private String name;
	private String nickname;
	private String pw;
	private String tel;
	private String image;
	private String rank;
	private Date join_dt;
}
