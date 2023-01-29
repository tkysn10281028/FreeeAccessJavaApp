package com.sono.proc.sample.database.dto;

import lombok.Data;

@Data
public class ClientInfoDatabaseDto {
	private String authorizationCode;
	private String clientId;
	private String clientSecret;
}
