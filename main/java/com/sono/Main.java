package com.sono;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;

import com.sono.execute.ExecuteMainProcess;

@Configuration
@ComponentScan
public class Main {
	public static void main(String[] args) {

		try (GenericApplicationContext context = new AnnotationConfigApplicationContext(Main.class)) {
			var executeMainProc = context.getBean(ExecuteMainProcess.class);
			executeMainProc.executeMainProcess(args);
		}
	}
}
