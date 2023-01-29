package com.sono.proc.common.interfaces;

import org.springframework.stereotype.Component;

import com.sono.proc.sample.dto.MainProcParameterDto;

@Component
public interface EntryPoint {
	public void entry(String[] params);

	public MainProcParameterDto validateParams(String[] params);
}
