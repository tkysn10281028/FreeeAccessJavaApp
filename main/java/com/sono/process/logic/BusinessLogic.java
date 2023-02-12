package com.sono.process.logic;

import org.springframework.stereotype.Component;

@Component
public interface BusinessLogic {
	public void beforeProcess();

	public void mainProcess();

	public void afterProcess();
}
