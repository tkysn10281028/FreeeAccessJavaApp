package com.sono.process.impl;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sono.process.MainProcess;
import com.sono.process.dto.ClientInfoDto;
import com.sono.process.logic.SampleBusinessLogic;

@Component
public class MainSampleProcessImpl implements MainProcess {
	@Autowired
	SampleBusinessLogic businessLogic;
	Logger logger = LoggerFactory.getLogger(MainSampleProcessImpl.class);

	@Override
	public void execute(String[] parameters) {
		Arrays.asList(parameters).forEach((param) -> logger.info("Passed Argument:" + param));
		if (validateClientInfo(parameters)) {
			var clientDto = convertIntoClientInfo(parameters);
			businessLogic.executeSampleBusinessLogic(clientDto);
		} else {
			throw new IllegalArgumentException("Not Proper Argument.");
		}
	}

	public boolean validateClientInfo(String[] parameters) {
		return true;
	}

	public ClientInfoDto convertIntoClientInfo(String[] parameters) {
		ClientInfoDto dto = new ClientInfoDto();
		dto.setAuthorizationCode("AuthCode");
		dto.setClientId("clientCode");
		dto.setClientSecret("clientSecret");
		return dto;
	}

}
