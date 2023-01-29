package com.sono.proc.sample;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sono.proc.common.interfaces.EntryPoint;
import com.sono.proc.sample.database.logic.ClientInfoDatabaseBusinessLogic;
import com.sono.proc.sample.dto.MainProcParameterDto;
import com.sono.proc.sample.process.logic.AppProcessBusinessLogic;

@Component
public class SampleAppEntryPoint implements EntryPoint {
	@Autowired
	ClientInfoDatabaseBusinessLogic clientInfoDatabaseBusinessLogic;
	@Autowired
	AppProcessBusinessLogic appProcessBusinessLogic;

	@Override
	public void entry(String[] params) {
		var result = validateParams(params);
		var dataBaseResult = clientInfoDatabaseBusinessLogic.execute(result);
		if (StringUtils.isNotEmpty(dataBaseResult.getAuthorizationCode())
				&& StringUtils.isNotEmpty(dataBaseResult.getClientId())
				&& StringUtils.isNotEmpty(dataBaseResult.getClientSecret())) {
		} else {
			throw new RuntimeException("No info Found.");
		}
	}

	@Override
	public MainProcParameterDto validateParams(String[] params) {
		var outDto = new MainProcParameterDto();
		outDto.setAuthorizationCode("code");
		outDto.setClientId("client_id");
		outDto.setClientSecret("client_secret");
		return outDto;
	}
}