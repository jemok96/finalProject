package com.wegoing.util;

import java.util.List;

import org.springframework.stereotype.Component;

import com.wegoing.dto.ClubDTO;
import com.wegoing.dto.PrincipalDetails;
import com.wegoing.service.ClubMemberService;
import com.wegoing.service.MemberService;

@Component
public final class ClubUtil {
	private static ClubMemberService cms;
	private static MemberService mService;
	private ClubUtil(ClubMemberService cms,MemberService mService) {
		ClubUtil.cms = cms;
		ClubUtil.mService = mService;
	}
	public static List<ClubDTO> getClub(PrincipalDetails userDetails){
		String loginEmail = "";
		if( userDetails != null ) loginEmail = userDetails.getMdto().getEmail();
		String cstatus ="y";
		return cms.selectAll(loginEmail, cstatus);
	}
	public static String getAuth(PrincipalDetails userDetails) {
		String auth = "";
		if( userDetails != null ) auth = mService.getMemberAuth(userDetails.getMdto().getEmail());
		return auth;
	}
}
