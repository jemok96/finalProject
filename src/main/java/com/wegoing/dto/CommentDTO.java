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
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommentDTO {
	private Integer cmno;
	private Integer bno;
	private String cmwriter;
	private String cmcontent;
	private Date reg_dt;
	private Date up_dt;
}
