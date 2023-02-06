package com.wegoing.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wegoing.dao.DocumentDAO;
import com.wegoing.dto.DocumentDTO;
import com.wegoing.dto.StartEnd;

@Service
public class DocumentService {
	
	@Autowired
	DocumentDAO dao;
	 
	
	// 협업 공간 내 모든 문서 출력
	public List<DocumentDTO> getAll(int clno, int startNo){
		StartEnd se = StartEnd.builder()
							  .clno(clno)
							  .startNo(startNo)
							  .build();
		return dao.pagingSelect(se);
	}
	
	public int getTotal(int clno) {
		return dao.getTotal(clno);
	}

	public DocumentDTO getOne(int dno) {
		return dao.selectOne(dno);
	}

	public void modify(DocumentDTO dto) {
		dao.updateOne(dto);
		
	}
	
	public void remove(int dno) {
		dao.deleteOne(dno);
	}

	public void write(DocumentDTO dto) {
		dao.insertOne(dto);
		
	}
	
	
	
	
	
}
