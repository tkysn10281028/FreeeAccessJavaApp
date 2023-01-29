package com.sono.exec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.sono.proc.MainProc;

@Component
public class ExecuteMainProc {
	@Autowired
	@Qualifier("sampleAppMainProcImpl")
	MainProc proc;

	public void execute(String[] params) {
		proc.executeProcedure(params);
	}
}
