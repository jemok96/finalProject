package com.wegoing.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
public class BoardDTO {
	private Integer bno;
	private String email;
	private Integer cateno;
	private String btitle;
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
