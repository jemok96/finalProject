package com.wegoing.dto;

import java.util.Date;
import java.util.Objects;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileDTO {
	private Integer fno;
	private String fname;
	
	@NotBlank(message ="필수 입력입니다.")
	@Size(max = 50, message = "최대 50글자 입니다.")
	private String description;
	private String uuid;
	private String fpath;
	private Date reg_dt;
	private Integer clno;
	
	public FileDTO() {}

	@Builder
	public FileDTO(Integer fno, String fname,
			@NotBlank(message = "필수 입력입니다.") @Size(max = 50, message = "최대 50글자 입니다.") String description, String uuid,
			String fpath, Date reg_dt, Integer clno) {
		this.fno = fno;
		this.fname = fname;
		this.description = description;
		this.uuid = uuid;
		this.fpath = fpath;
		this.reg_dt = reg_dt;
		this.clno = clno;
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(clno, description, fname, fno, fpath, reg_dt, uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FileDTO other = (FileDTO) obj;
		return Objects.equals(clno, other.clno) && Objects.equals(description, other.description)
				&& Objects.equals(fname, other.fname) && Objects.equals(fno, other.fno)
				&& Objects.equals(fpath, other.fpath) && Objects.equals(reg_dt, other.reg_dt)
				&& Objects.equals(uuid, other.uuid);
	}

	@Override
	public String toString() {
		return "FileDTO [fno=" + fno + ", fname=" + fname + ", description=" + description + ", uuid=" + uuid
				+ ", fpath=" + fpath + ", reg_dt=" + reg_dt + ", clno=" + clno + "]";
	}
	

	
	
}
