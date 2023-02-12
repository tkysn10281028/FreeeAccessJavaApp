package com.sono.process.logic;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sono.process.data.DataBaseAccessLogic;
import com.sono.process.data.FreeeAppAccessLogic;
import com.sono.process.dto.ClientInfoDto;

@Component
public class SampleBusinessLogic implements BusinessLogic {
	@Autowired
	FreeeAppAccessLogic freeeAppAccessLogic;
	@Autowired
	DataBaseAccessLogic dataBaseAccessLogic;

	private ClientInfoDto clientInfoDto;
	private String accessToken;
	private Integer companyId;

	public void executeSampleBusinessLogic(ClientInfoDto clientInfoDto) {
		this.clientInfoDto = clientInfoDto;
		this.beforeProcess();
		this.mainProcess();
		this.afterProcess();
	}

	@Override
	public void beforeProcess() {
		var refreshToken = dataBaseAccessLogic.getRefreshToken(clientInfoDto);
		if (StringUtils.isNotEmpty(refreshToken)) {
			freeeAppAccessLogic.accessSecondOrMoreTime(clientInfoDto);
		} else {
			freeeAppAccessLogic.accessFirstTime(this.clientInfoDto);
		}
		this.accessToken = freeeAppAccessLogic.getAccessToken();
		this.companyId = freeeAppAccessLogic.getCompanyId();
	}

	@Override
	public void mainProcess() {

	}

	@Override
	public void afterProcess() {
	}

}
