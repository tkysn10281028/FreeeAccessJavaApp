package com.sono.proc.sample.dto;

import com.sono.proc.common.parents.CommonDto;

import lombok.Data;

@Data
public class MainProcParameterDto extends CommonDto {
	private String authorizationCode;
	private String clientId;
	private String clientSecret;

}
