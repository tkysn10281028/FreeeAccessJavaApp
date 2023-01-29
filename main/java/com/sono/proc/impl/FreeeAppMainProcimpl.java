package com.sono.proc.impl;

import org.springframework.stereotype.Component;

import com.sono.proc.MainProc;
import com.sono.proc.common.interfaces.EntryPoint;
import com.sono.proc.freee.FreeeAppEntryPoint;

@Component
public class FreeeAppMainProcimpl implements MainProc {
	@Override
	public void executeProcedure(String[] params) {
		entryPoint().entry(params);
	}

	public EntryPoint entryPoint() {
		return new FreeeAppEntryPoint();
	}
}
