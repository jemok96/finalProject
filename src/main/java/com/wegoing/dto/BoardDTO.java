package com.wegoing.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardDTO {

	private Integer bno;
	private String email;
	private Integer cateno;
	@NotBlank
	@NotNull
	private String btitle;
	@NotBlank
	@NotNull
	private String bcontent;
	private Integer bhits;
	private Date reg_dt;
	private Date up_dt;
	private String nickname;
	public BoardDTO(){}
	@Builder
	public BoardDTO(Integer bno, String email, Integer cateno, @NotBlank @NotNull String btitle,
			@NotBlank @NotNull String bcontent, Integer bhits, Date reg_dt, Date up_dt, String nickname) {
		this.bno = bno;
		this.email = email;
		this.cateno = cateno;
		this.btitle = btitle;
		this.bcontent = bcontent;
		this.bhits = bhits;
		this.reg_dt = reg_dt;
		this.up_dt = up_dt;
		this.nickname = nickname;
	}
	
	
	
}
