package com.wegoing.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.wegoing.dto.FileDTO;

@Repository
@Mapper
public interface FileDao {
	List<FileDTO> getFileList(int clno);
	int uploadFile(FileDTO dto);
	int deleteFile(int fno);
}
