package com.sono.process;

import org.springframework.stereotype.Component;

@Component
public interface MainProcess {
	public void execute(String[] parameters);
}
