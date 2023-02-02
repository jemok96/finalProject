package com.wegoing.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

public class PageUtil {
	public static Map<String, Object> getPageData(int totalCount, int currentPage) {
		Map<String, Object> map = new HashMap<String, Object>();

		int totalPage = (totalCount % 7 == 0) ? totalCount / 7 : totalCount / 7 + 1;

		int startNo = (currentPage - 1) * 7;
		int endNo = currentPage * 7;

		int startPage = 1;
		int endPage = totalPage;

		// 시작페이지 미세조정

		if (currentPage <= 5) {
			startPage = 1;
		} else if (currentPage >= 6) {
			startPage = currentPage - 4;
		}

		if (totalPage - currentPage <= 5) {
			endPage = totalPage;
		} else if (totalPage - currentPage > 5) {
			if (currentPage <= 5) {
				if (totalPage > 10) {
					endPage = 10;
				} else {
					endPage = totalPage;
				}
			} else {
				endPage = currentPage + 4;
			}
		}

		boolean isNext = false;
		boolean isPre = false;

		// 현재 페이지 번호에서 5를 뺀값이 0이상이면 이전 값이 존재

		if (currentPage - 5 > 0)
			isPre = true;

		// 현재 페이지 번호에서 5를 더한값이 끝페이지 번호보다 작으면 다음이 존재
		if (currentPage + 5 <= totalPage)
			isNext = true;

		// 맵객체에 담기
		// map.put("키", value);
		
		map.put("startNo", startNo);
		map.put("endNo", endNo);
		map.put("currentPage", currentPage);
//		map.put("recordPerPage", recordPerPage);
		map.put("totalPage", totalPage);
		map.put("totalCount", totalCount);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("isNext", isNext);
		map.put("isPre", isPre);

		return map;
	}
}
