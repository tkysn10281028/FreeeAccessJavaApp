package com.sono.proc.common.interfaces;

import org.springframework.stereotype.Component;

@Component
public interface BusinessLogicCommon {
	public void executeBeforeProcess();

	public void executeMainProcess();

	public void executeAfterProcess();

}
