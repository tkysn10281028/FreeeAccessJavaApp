package com.sono.proc.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.sono.proc.MainProc;
import com.sono.proc.common.interfaces.EntryPoint;

@Component
public class SampleAppMainProcImpl implements MainProc {
	@Autowired
	@Qualifier("sampleAppEntryPoint")
	EntryPoint entryPoint;

	@Override
	public void executeProcedure(String[] params) {
		entryPoint.entry(params);
	}
}
