package com.sono.execute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.sono.process.MainProcess;

@Component
public class ExecuteMainProcess {
	@Autowired
	@Qualifier("methodUtilsProcessImpl")
	MainProcess mainProcess;

	public void executeMainProcess(String[] paramters) {
		mainProcess.execute(paramters);
	}
}
