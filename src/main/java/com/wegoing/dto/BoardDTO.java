package com.wegoing.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

	
	
}
