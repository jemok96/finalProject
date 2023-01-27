package com.wegoing.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
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

	
	
}
