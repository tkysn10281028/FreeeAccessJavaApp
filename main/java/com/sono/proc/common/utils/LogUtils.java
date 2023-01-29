package com.sono.proc.common.utils;

import java.util.Arrays;

import org.springframework.stereotype.Component;

@Component
public class LogUtils {
	public void printDto(Object obj) {
		System.out.println("------------------printDto start------------------");
		var steList = Arrays.asList(new Throwable().getStackTrace());
		if (steList.size() >= 2) {
			System.out.println("method called : " + steList.get(1));
		} else {
			System.out.println("No Method Called.");
		}
		System.out.println("---------Object---------" + "\r\n"
				+ obj.toString().replaceAll(" ", "").replaceAll("\\(", "\r\n\\(\r\n").replaceAll("\\)", "\r\n\\)")
						.replaceAll("\\[", "\r\n\\[\r\n").replaceAll("\\]", "\r\n\\]").replaceAll(",", "\r\n")
						.replaceAll("=", " = "));
		System.out.println("---------Object end---------");
		System.out.println("------------------printDto end------------------" + "\r\n");
	}

	public void printArray(String[] obj) {
		System.out.println("------------------printArray start------------------");
		var steList = Arrays.asList(new Throwable().getStackTrace());
		if (steList.size() >= 2) {
			System.out.println("method called : " + steList.get(1));
		} else {
			System.out.println("No Method Called.");
		}
		System.out.println("---------Object---------");
		for (String str : obj) {
			System.out.println(str);
		}
		System.out.println("---------Object end---------");
		System.out.println("------------------printArray end------------------" + "\r\n");
	}

	public void printMethod() {
		System.out.println("------------------printMethod start------------------");
		var steList = Arrays.asList(new Throwable().getStackTrace());
		if (steList.size() >= 2) {
			System.out.println("method called : " + steList.get(1));
		} else {
			System.out.println("No Method Called.");
		}
		System.out.println("------------------printMethod end------------------" + "\r\n");
	}
}
