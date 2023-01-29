package com.sono.proc.common.parents;

public class CommonDto {
	@Override
	public String toString() {
		// TODO 自動生成されたメソッド・スタブ
		return super.toString().replaceAll("¥¥(", "¥r¥n¥¥(").replaceAll("¥¥)", "¥r¥n¥¥)").replaceAll(",", "¥r¥n");
	}
}
