package com.wegoing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wegoing.dao.PartnerDAO;
import com.wegoing.dto.PartnerDTO;

@Service
public class PartnerService {
	PartnerDAO pao;
	int result = 0; 
	
	@Autowired
	public PartnerService(PartnerDAO pao) {
		this.pao = pao;
	}

	public int isPartner(String partnerEmail, String loginEmail) {
		result = pao.existsPartner(partnerEmail, loginEmail);
		return result;
	}

	public void enrollPartner(PartnerDTO pto) {
		pao.insertOne(pto);
		
	}

	public int registerMe(String partnerEmail, String loginEmail) {
		result = pao.enrollMe(partnerEmail,loginEmail);
		return result;
	}

	public void updatePstatus(PartnerDTO pto) {
		pao.modifyPstatus(pto);
	}
}
