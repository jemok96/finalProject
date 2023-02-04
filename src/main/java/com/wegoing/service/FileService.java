package com.wegoing.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wegoing.dao.FileDao;
import com.wegoing.dto.FileDTO;

@Service
public class FileService {
	private final FileDao dao;
	public FileService(FileDao dao) {
		this.dao = dao;
	}
	
	
	public List<FileDTO> getFileList(int clno){
		return dao.getFileList(clno);
	}
	
	public int uploadFile(FileDTO dto) {
		return dao.uploadFile(dto);
	}
	public int deleteFile(int fno) {
		return dao.deleteFile(fno);
	}
}
