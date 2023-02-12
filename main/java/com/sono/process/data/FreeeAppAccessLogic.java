package com.sono.process.data;

import org.springframework.stereotype.Component;

import com.sono.process.dto.ClientInfoDto;

@Component
public class FreeeAppAccessLogic {
	private String refreshToken;
	private Integer companyId;
	private String accessToken;

	public void accessFirstTime(ClientInfoDto clientInfoDto) {
		this.accessToken = "KARI-ACCESS-FIRST=TOKEN:" + clientInfoDto.getAuthorizationCode()
				+ clientInfoDto.getClientId() + clientInfoDto.getClientSecret();
		this.companyId = 12345;
	}

	public void accessSecondOrMoreTime(ClientInfoDto clientInfoDto) {
		this.accessToken = "KARI-ACCESS-SECOND=TOKEN:" + clientInfoDto.getAuthorizationCode()
				+ clientInfoDto.getClientId() + clientInfoDto.getClientSecret();
		this.companyId = 12345;
	}

	public String getAccessToken() {
		return this.accessToken;
	}

	public Integer getCompanyId() {
		return this.companyId;
	}
}
