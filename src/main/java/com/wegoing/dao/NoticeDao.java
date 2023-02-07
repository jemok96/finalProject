package com.wegoing.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.wegoing.dto.NoticeDTO;

@Repository
@Mapper
public interface NoticeDao {
	List<NoticeDTO> getNoticeList(Map<String, Integer> map);
	int getNoticeCount();
	int writeNotice(NoticeDTO notice);
}
