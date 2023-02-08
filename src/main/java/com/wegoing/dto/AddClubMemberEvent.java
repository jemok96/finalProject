package com.wegoing.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AddClubMemberEvent {
	
	private final ClubMemberDTO clubmember;
	private final ClubDTO club;
}
