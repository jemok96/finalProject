package com.wegoing.dto;

import java.util.Date;
import java.util.Objects;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

	
	
	
	@Override
	public String toString() {
		return "CommentDTO [cmno=" + cmno + ", bno=" + bno + ", cmwriter=" + cmwriter + ", cmcontent=" + cmcontent
				+ ", reg_dt=" + reg_dt + ", up_dt=" + up_dt + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(bno, cmcontent, cmno, cmwriter, reg_dt, up_dt);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CommentDTO other = (CommentDTO) obj;
		return Objects.equals(bno, other.bno) && Objects.equals(cmcontent, other.cmcontent)
				&& Objects.equals(cmno, other.cmno) && Objects.equals(cmwriter, other.cmwriter)
				&& Objects.equals(reg_dt, other.reg_dt) && Objects.equals(up_dt, other.up_dt);
	}
	
	
	
}
