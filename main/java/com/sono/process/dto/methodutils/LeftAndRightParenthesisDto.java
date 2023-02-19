package com.sono.process.dto.methodutils;

import lombok.Data;

@Data
public class LeftAndRightParenthesisDto {
	private Integer leftParenthesisCount;
	private Integer rightParenthesisCount;
	private Integer leftParenthesisPosition;
	private Integer rightParenthesisPosition;

	public LeftAndRightParenthesisDto(Integer leftParenthesisCount, Integer rightParenthesisCount,
			Integer leftParenthesisPosition, Integer rightParenthesisPosition) {
		this.leftParenthesisCount = leftParenthesisCount;
		this.rightParenthesisCount = rightParenthesisCount;
		this.leftParenthesisPosition = leftParenthesisPosition;
		this.rightParenthesisPosition = rightParenthesisPosition;
	}
}
