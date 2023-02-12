package com.sono.process.dto.methodutils;

import java.util.List;

import lombok.Data;

@Data
public class MethodInfoDto {
	private String methodName;
	private List<String> methodPart;
	private List<String> methodSignature;
	private String methodReturnVal;
	private List<MethodInfoDto> methodInfoList;
}
