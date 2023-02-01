package com.wegoing.dto;

import java.util.Date;
import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDTO {

	private Integer bno;
	private String email;
	@NotNull(message="게시판을 선택해주세요")
	private Integer cateno;
	@NotBlank(message="제목을 입력하세요")
	@Size(max=30,message="최대30자 입니다")
	private String btitle;
	@NotBlank(message="내용을 입력하세요")
	private String bcontent;
	private Integer bhits;
	private Date reg_dt;
	private Date up_dt;
	private String nickname;
	public BoardDTO(){}
	@Builder
	public BoardDTO(Integer bno, String email, @NotNull(message="게시판을 선택해주세요") Integer cateno,
			@NotBlank(message = "제목을 입력하세요") @Size(max = 30, message = "최대30자 입니다") String btitle,
			@NotBlank(message="내용을 입력하세요") String bcontent, Integer bhits, Date reg_dt, Date up_dt, String nickname) {
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
	
	
	
	
	@Override
	public String toString() {
		return "BoardDTO [bno=" + bno + ", email=" + email + ", cateno=" + cateno + ", btitle=" + btitle + ", bcontent="
				+ bcontent + ", bhits=" + bhits + ", reg_dt=" + reg_dt + ", up_dt=" + up_dt + ", nickname=" + nickname
				+ "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(bcontent, bhits, bno, btitle, cateno, email, nickname, reg_dt, up_dt);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BoardDTO other = (BoardDTO) obj;
		return Objects.equals(bcontent, other.bcontent) && Objects.equals(bhits, other.bhits)
				&& Objects.equals(bno, other.bno) && Objects.equals(btitle, other.btitle)
				&& Objects.equals(cateno, other.cateno) && Objects.equals(email, other.email)
				&& Objects.equals(nickname, other.nickname) && Objects.equals(reg_dt, other.reg_dt)
				&& Objects.equals(up_dt, other.up_dt);
	}
	
	
	
	
	
}
