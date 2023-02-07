package com.wegoing.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.wegoing.dto.NoticeDTO;

@Repository
@Mapper
public interface NoticeDao {
	List<NoticeDTO> getNoticeList();
	int getNoticeCount();
	int writeNotice(NoticeDTO notice);
}
