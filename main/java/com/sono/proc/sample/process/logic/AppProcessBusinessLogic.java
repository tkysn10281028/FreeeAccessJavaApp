package com.sono.proc.sample.process.logic;

import org.springframework.stereotype.Component;

import com.sono.proc.common.interfaces.BusinessLogicCommon;

@Component
public class AppProcessBusinessLogic implements BusinessLogicCommon {
	public void execute() {
		executeBeforeProcess();
		executeMainProcess();
		executeAfterProcess();
	}

	@Override
	public void executeBeforeProcess() {
	}

	@Override
	public void executeMainProcess() {
	}

	@Override
	public void executeAfterProcess() {
	}

}
