package com.wegoing.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class BoardDTO {
	private Integer bno;
	private String email;
	private Integer cateno;
	@NotBlank
	private String btitle;
	@NotBlank
	private String bcontent;
	private Integer bhits;
	private Date reg_dt;
	private Date up_dt;
	private String nickname;
	public BoardDTO() {}
	public BoardDTO(String email, Integer cateno, String btitle, String bcontent, String nickname) {
		this.email = email;
		this.cateno = cateno;
		this.btitle = btitle;
		this.bcontent = bcontent;
		this.nickname = nickname;
	}

	
	
}
