package com.wegoing.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.wegoing.dto.DocumentDTO;
import com.wegoing.dto.StartEnd;

@Repository
@Mapper
public interface DocumentDAO {
	// 특정 협업공간에 문서 리스트 예) clno = 4번 -> 4번 안에 문서들
	public List<DocumentDTO> pagingSelect(StartEnd se);
	public int getTotal(int clno);
	public DocumentDTO selectOne(int dno);
}
