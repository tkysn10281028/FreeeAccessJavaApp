package com.sono.process.dto.methodutils;

import java.util.List;

import lombok.Data;

@Data
public class ClassInfoDto {
	private String className;
	private List<String> classPart;
}
