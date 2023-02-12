package com.sono.process.dto;

import lombok.Data;

@Data
public class ClientInfoDto {
	private String clientId;
	private String clientSecret;
	private String authorizationCode;
}
