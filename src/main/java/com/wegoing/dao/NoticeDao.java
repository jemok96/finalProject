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
	NoticeDTO getNoticeOne(int notino);
	int getNoticeCount();
	int writeNotice(NoticeDTO notice);
	int updateHits(int notino);
	int noticeUpdate(NoticeDTO notice);
	int noticeDelete(int notino);
}
