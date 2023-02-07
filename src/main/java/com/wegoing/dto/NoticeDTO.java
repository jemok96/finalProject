package com.wegoing.dto;

import java.util.Date;
import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoticeDTO {
	private Integer notino;
	
	@NotBlank(message = "제목은 필수입니다.")
	@Size(max = 50, message = "50자 이내로 입력해주세요")
	private String ntitle;
	@NotBlank(message =  "내용은 필수입니다.")
	private String ncontent;
	private Date reg_dt;
	private Date up_dt;
	private Integer nhits;
	private String email;
	private String importance;
	
	public NoticeDTO() {}
	@Builder
	public NoticeDTO(Integer notino,@NotBlank(message = "제목은 필수입니다.")@Size(max = 50, message = "50자 이내로 입력해주세요")String ntitle,
			@NotBlank(message =  "내용은 필수입니다.") String ncontent, Date reg_dt, Date up_dt, Integer nhits, String email, String importance) {
		this.notino = notino;
		this.ntitle = ntitle;
		this.ncontent = ncontent;
		this.reg_dt = reg_dt;
		this.up_dt = up_dt;
		this.nhits = nhits;
		this.email = email;
		this.importance = importance;
	}
	
	
	
	@Override
	public int hashCode() {
		return Objects.hash(email, importance, ncontent, nhits, notino, ntitle, reg_dt, up_dt);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NoticeDTO other = (NoticeDTO) obj;
		return Objects.equals(email, other.email) && Objects.equals(importance, other.importance)
				&& Objects.equals(ncontent, other.ncontent) && Objects.equals(nhits, other.nhits)
				&& Objects.equals(notino, other.notino) && Objects.equals(ntitle, other.ntitle)
				&& Objects.equals(reg_dt, other.reg_dt) && Objects.equals(up_dt, other.up_dt);
	}



	@Override
	public String toString() {
		return "NoticeDTO [notino=" + notino + ", ntitle=" + ntitle + ", ncontent=" + ncontent + ", reg_dt=" + reg_dt
				+ ", up_dt=" + up_dt + ", nhits=" + nhits + ", email=" + email + ", importance=" + importance + "]";
	}



	
	
	
	
	
	
}
