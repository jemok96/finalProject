package com.wegoing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wegoing.dao.DocumentDAO;
import com.wegoing.dto.DocumentDTO;


@Service
public class KanbanService {
	private final DocumentDAO dao;
	
	@Autowired
	public KanbanService(DocumentDAO dao) {
		this.dao = dao;
	}

//	public List<DocumentDTO> getAllList(int clno) {
//		return dao.selectAllList(clno);
//	}

	public List<DocumentDTO> getDstatusList(String dstatus, int clno) {
		return dao.selectDstatusList(dstatus, clno);
	}

	
}
