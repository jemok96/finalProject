package com.wegoing.util;

import java.util.List;

import org.springframework.stereotype.Component;

import com.wegoing.dto.ClubDTO;
import com.wegoing.dto.PrincipalDetails;
import com.wegoing.service.ClubMemberService;

@Component
public final class ClubUtil {
	private static ClubMemberService cms;
	
	private ClubUtil(ClubMemberService cms) {
		ClubUtil.cms = cms;
	}
	public static List<ClubDTO> getClub(PrincipalDetails userDetails){
		String loginEmail = "";
		if( userDetails != null ) loginEmail = userDetails.getMdto().getEmail();
		String cstatus ="y";
		return cms.selectAll(loginEmail, cstatus);
	}
}
