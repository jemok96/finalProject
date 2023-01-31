package com.wegoing.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommentDTO {
	private Integer cmno;
	private Integer bno;
	private String cmwriter;
	private String cmcontent;
	private Date reg_dt;
	private Date up_dt;
	
	public CommentDTO(){}

	@Builder
	public CommentDTO(Integer cmno, Integer bno, String cmwriter, String cmcontent, Date reg_dt, Date up_dt) {
		this.cmno = cmno;
		this.bno = bno;
		this.cmwriter = cmwriter;
		this.cmcontent = cmcontent;
		this.reg_dt = reg_dt;
		this.up_dt = up_dt;
	}
	
	
}
