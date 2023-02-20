package com.sono.process.logic;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import com.sono.process.dto.methodutils.ClassInfoDto;
import com.sono.process.dto.methodutils.LeftAndRightParenthesisDto;
import com.sono.process.dto.methodutils.ReservedWordDto;

public class MethodUtilsBusinessLogicTest {
	MethodUtilsBusinessLogic methodUtilsBusinessLogic = new MethodUtilsBusinessLogic();
	private String[] array = { "try", "{", "}", "CLASSNAME", "(", ")", "{", "}", "if", "(", "SIG", ")", "{", "}", "for",
			"(", "SIG1,", "SIG2", ")", "{", "CONTENT", "}", "CLASSNAME", "(", "String", "SIG1,", "String", "SIG2", ")",
			"{", "CONTENT", "}", "void", "F", "(", "String", "SIG1,", "String", "SIG2", ")", "{", "CONTENT", "}",
			"public", "String", "G", "(", "String", "SIG1,", "String", "SIG2", ")", "{", "CONTENT", "}", "public",
			"String", "H", "(", "String", "SIG1,", "String", "SIG2", ")", "{", "public", "void", "I", "(", "String",
			"SIG1,", "String", "SIG2", ")", "{", "CONTENT1;", "}", "CONTENT2;", "CONTENT3;", "}" };
	{
		this.methodUtilsBusinessLogic.classInfoDto = new ClassInfoDto();
		this.methodUtilsBusinessLogic.classInfoDto.setClassName("CLASSNAME");
		this.methodUtilsBusinessLogic.reservedWordDto = new ReservedWordDto();

	}

	@Test
	public void testScanAllRoundParenthesis() {

		var targetList = Arrays.asList("(", ")", "", "", "", "", "", "", "", "", "", "");
		var targetList1 = Arrays.asList("(", "(", ")", ")", "(", "(", "(", ")", ")", ")", "(", ")");
		var targetList2 = Arrays.asList("(", "(", "a", "あ", "b", "い", ")", ")", "(", "(", ")", ")");
		var targetList3 = Arrays.asList("(", "(", "a", ")", "b", "c", ")", "{", "{", "(", "a", ")");
		var targetList4 = Arrays.asList("a", "b", "c", "(", "(", "(", ")", ")", "(", "a", ")", ")");
		var targetList5 = Arrays.asList("(", ")", ")", "", "", "", "", "", "", "", "", "");
		var targetList6 = Arrays.asList("(", "a", "b", ")", ")", "", "", "", "", "", "", "");
		var targetList7 = Arrays.asList("(", ")", "(", "a", "b", ")", ")", "(", ")", "", "", "");
		var targetList8 = Arrays.asList("(", "a", "b", "c", ")", "()", "(", "a", "(", "b", ")", "(");
		var targetList9 = Arrays.asList("a", "b", "(", "(", "a", ")", "(", ")", "(", ")", ")", ")");
		var targetList10 = Arrays.asList("", "", "", "", "", "", "", "", "", "", "", "");

		assertTrue(methodUtilsBusinessLogic.scanRoundParenthesis(targetList, targetList.size()));
		assertTrue(methodUtilsBusinessLogic.scanRoundParenthesis(targetList1, targetList1.size()));
		assertTrue(methodUtilsBusinessLogic.scanRoundParenthesis(targetList2, targetList1.size()));
		assertTrue(methodUtilsBusinessLogic.scanRoundParenthesis(targetList3, targetList3.size()));
		assertTrue(methodUtilsBusinessLogic.scanRoundParenthesis(targetList4, targetList4.size()));
		assertTrue(!methodUtilsBusinessLogic.scanRoundParenthesis(targetList5, targetList5.size()));
		assertTrue(!methodUtilsBusinessLogic.scanRoundParenthesis(targetList6, targetList6.size()));
		assertTrue(!methodUtilsBusinessLogic.scanRoundParenthesis(targetList7, targetList7.size()));
		assertTrue(!methodUtilsBusinessLogic.scanRoundParenthesis(targetList8, targetList8.size()));
		assertTrue(!methodUtilsBusinessLogic.scanRoundParenthesis(targetList9, targetList9.size()));
		assertTrue(methodUtilsBusinessLogic.scanRoundParenthesis(targetList10, targetList10.size()));

	}

	@Test
	public void testScanAllMiddleParenthesis() {

		var targetList = Arrays.asList("{", "}", "", "", "", "", "", "", "", "", "", "");
		var targetList1 = Arrays.asList("{", "{", "}", "}", "{", "{", "{", "}", "}", "}", "{", "}");
		var targetList2 = Arrays.asList("{", "{", "a", "あ", "b", "い", "}", "}", "{", "{", "}", "}");
		var targetList3 = Arrays.asList("{", "{", "a", "}", "b", "c", "}", "(", "(", "{", "a", "}");
		var targetList4 = Arrays.asList("a", "b", "c", "{", "{", "{", "}", "}", "{", "a", "}", "}");
		var targetList5 = Arrays.asList("{", "}", "}", "", "", "", "", "", "", "", "", "");
		var targetList6 = Arrays.asList("{", "a", "b", "}", "}", "", "", "", "", "", "", "");
		var targetList7 = Arrays.asList("{", "}", "{", "a", "b", "}", "}", "{", "}", "", "", "");
		var targetList8 = Arrays.asList("{", "a", "b", "c", "}", "()", "{", "a", "{", "b", "}", "{");
		var targetList9 = Arrays.asList("a", "b", "{", "{", "a", "}", "{", "}", "{", "}", "}", "}");
		var targetList10 = Arrays.asList("", "", "", "", "", "", "", "", "", "", "", "");

		assertTrue(methodUtilsBusinessLogic.scanMiddleParenthesis(targetList, targetList.size()));
		assertTrue(methodUtilsBusinessLogic.scanMiddleParenthesis(targetList1, targetList1.size()));
		assertTrue(methodUtilsBusinessLogic.scanMiddleParenthesis(targetList2, targetList2.size()));
		assertTrue(methodUtilsBusinessLogic.scanMiddleParenthesis(targetList3, targetList3.size()));
		assertTrue(methodUtilsBusinessLogic.scanMiddleParenthesis(targetList4, targetList4.size()));
		assertTrue(!methodUtilsBusinessLogic.scanMiddleParenthesis(targetList5, targetList5.size()));
		assertTrue(!methodUtilsBusinessLogic.scanMiddleParenthesis(targetList6, targetList6.size()));
		assertTrue(!methodUtilsBusinessLogic.scanMiddleParenthesis(targetList7, targetList7.size()));
		assertTrue(!methodUtilsBusinessLogic.scanMiddleParenthesis(targetList8, targetList8.size()));
		assertTrue(!methodUtilsBusinessLogic.scanMiddleParenthesis(targetList9, targetList9.size()));
		assertTrue(methodUtilsBusinessLogic.scanMiddleParenthesis(targetList10, targetList10.size()));
	}

	@Test
	public void testGenerateMethodInfoList1() {
		String[] testArray = { "{", "A", "}", "{", "B", "C", "}", "{", "D", "E", "F", "}", "{", "}" };
		var result1 = methodUtilsBusinessLogic.generateMethodImplementsLoop(Arrays.asList(testArray), 0);
		assertEquals(result1.size(), 4);
		assertEquals(result1.get(0).getMethodInfoList().size(), 0);
		assertEquals(result1.get(1).getMethodInfoList().size(), 0);
		assertEquals(result1.get(2).getMethodInfoList().size(), 0);
		assertEquals(result1.get(3).getMethodInfoList().size(), 0);
	}

	@Test
	public void testGenerateMethodInfoList2() {
		String[] testArray = { "{", "A", "{", "B", "}", "C", "}", "{", "D", "{", "{", "E", "}", "}", "}" };
		var result1 = methodUtilsBusinessLogic.generateMethodImplementsLoop(Arrays.asList(testArray), 0);
		assertEquals(result1.size(), 2);
		assertEquals(result1.get(0).getMethodInfoList().size(), 1);
		assertEquals(result1.get(1).getMethodInfoList().size(), 1);
		assertEquals(result1.get(0).getMethodInfoList().get(0).getMethodInfoList().size(), 0);
		assertEquals(result1.get(1).getMethodInfoList().get(0).getMethodInfoList().size(), 1);
		assertEquals(result1.get(1).getMethodInfoList().get(0).getMethodInfoList().get(0).getMethodInfoList().size(),
				0);

	}

	@Test
	public void testGenerateMethodInfoList3() {
		String[] testArray = { "{", "A", "{", "B", "C", "{", "C", "{", "}", "D", "}", "{", "E", "}", "}", "F", "}" };
		var result1 = methodUtilsBusinessLogic.generateMethodImplementsLoop(Arrays.asList(testArray), 0);
		assertEquals(1, result1.size());
		assertEquals(result1.get(0).getMethodInfoList().size(), 1);
		assertEquals(result1.get(0).getMethodInfoList().get(0).getMethodInfoList().size(), 2);
		assertEquals(result1.get(0).getMethodInfoList().get(0).getMethodInfoList().get(0).getMethodInfoList().size(),
				1);
		assertEquals(result1.get(0).getMethodInfoList().get(0).getMethodInfoList().get(1).getMethodInfoList().size(),
				0);
	}

	@Test
	public void testGenerateFirstAndLastParenthesisCount() {
		String[] array = { "A", "(", ")", "{", "B", "(", "C", "D", ")", "{", "}", "E", "(", "F", "(", "G", "H", ")",
				")", "{", "}", "}" };
		var result1 = methodUtilsBusinessLogic.generateFirstAndLastParenthesisCount(Arrays.asList(array), 3);
		assertEquals(1, (int) result1.getLeftParenthesisPosition());
		assertEquals(2, (int) result1.getRightParenthesisPosition());
		result1 = methodUtilsBusinessLogic.generateFirstAndLastParenthesisCount(Arrays.asList(array), 9);
		assertEquals(5, (int) result1.getLeftParenthesisPosition());
		assertEquals(8, (int) result1.getRightParenthesisPosition());

		result1 = methodUtilsBusinessLogic.generateFirstAndLastParenthesisCount(Arrays.asList(array), 19);
		assertEquals(12, (int) result1.getLeftParenthesisPosition());
		assertEquals(18, (int) result1.getRightParenthesisPosition());

		String[] array1 = { "{", "A", "}", "{", "B", "C", "}", "{", "D", "E", "F", "}", "{", "}" };
		result1 = methodUtilsBusinessLogic.generateFirstAndLastParenthesisCount(Arrays.asList(array1), 7);
		assertEquals(0, (int) result1.getLeftParenthesisPosition());
		assertEquals(0, (int) result1.getRightParenthesisPosition());

		String[] array2 = { "{", "A", "}", "T", "(", ")", ")", "{", "B", "C", "}", "(", "(", ")", "", "{", "D", "E",
				"F", "}", "{", "}" };
		result1 = methodUtilsBusinessLogic.generateFirstAndLastParenthesisCount(Arrays.asList(array2), 7);
		assertNull(result1);
		String[] array3 = { "{", "A", "}", "T", "(", ")", "", "{", "B", "C", "}", "(", "(", ")", "", "{", "D", "E", "F",
				"}", "{", "}" };
		result1 = methodUtilsBusinessLogic.generateFirstAndLastParenthesisCount(Arrays.asList(array3), 15);
		assertNull(result1);

	}

	@Test
	public void testGenerateMethodName() {
		assertNull(methodUtilsBusinessLogic.generateMethodName(Arrays.asList(this.array),
				new LeftAndRightParenthesisDto(0, 0, 0, 0)));
		assertEquals("CLASSNAME", methodUtilsBusinessLogic.generateMethodName(Arrays.asList(this.array),
				new LeftAndRightParenthesisDto(4, 5, 0, 0)));
		assertEquals("if", methodUtilsBusinessLogic.generateMethodName(Arrays.asList(this.array),
				new LeftAndRightParenthesisDto(9, 11, 0, 0)));
		assertEquals("for", methodUtilsBusinessLogic.generateMethodName(Arrays.asList(this.array),
				new LeftAndRightParenthesisDto(15, 18, 0, 0)));
		assertEquals("CLASSNAME", methodUtilsBusinessLogic.generateMethodName(Arrays.asList(this.array),
				new LeftAndRightParenthesisDto(23, 28, 0, 0)));
		assertEquals("F", methodUtilsBusinessLogic.generateMethodName(Arrays.asList(this.array),
				new LeftAndRightParenthesisDto(34, 39, 0, 0)));
		assertEquals("G", methodUtilsBusinessLogic.generateMethodName(Arrays.asList(this.array),
				new LeftAndRightParenthesisDto(46, 51, 0, 0)));
		assertEquals("H", methodUtilsBusinessLogic.generateMethodName(Arrays.asList(this.array),
				new LeftAndRightParenthesisDto(58, 63, 0, 0)));
		assertEquals("I", methodUtilsBusinessLogic.generateMethodName(Arrays.asList(this.array),
				new LeftAndRightParenthesisDto(68, 73, 0, 0)));

	}

	@Test
	public void testGenerateMethodSignature() {
		assertNull(methodUtilsBusinessLogic.generateMethodSignature(Arrays.asList(this.array),
				new LeftAndRightParenthesisDto(0, 0, 0, 0)));
		assertNull(methodUtilsBusinessLogic.generateMethodSignature(Arrays.asList(this.array),
				new LeftAndRightParenthesisDto(4, 5, 0, 0)));
		assertEquals(Arrays.asList("SIG"), methodUtilsBusinessLogic.generateMethodSignature(Arrays.asList(this.array),
				new LeftAndRightParenthesisDto(9, 11, 0, 0)));
		assertEquals(Arrays.asList("SIG", "SIG2"), methodUtilsBusinessLogic
				.generateMethodSignature(Arrays.asList(this.array), new LeftAndRightParenthesisDto(15, 18, 0, 0)));
		assertEquals(Arrays.asList("String SIG", "String SIG2"), methodUtilsBusinessLogic
				.generateMethodSignature(Arrays.asList(this.array), new LeftAndRightParenthesisDto(23, 28, 0, 0)));
		assertEquals(Arrays.asList("String SIG", "String SIG2"), methodUtilsBusinessLogic
				.generateMethodSignature(Arrays.asList(this.array), new LeftAndRightParenthesisDto(34, 39, 0, 0)));
		assertEquals(Arrays.asList("String SIG", "String SIG2"), methodUtilsBusinessLogic
				.generateMethodSignature(Arrays.asList(this.array), new LeftAndRightParenthesisDto(46, 51, 0, 0)));
		assertEquals(Arrays.asList("String SIG", "String SIG2"), methodUtilsBusinessLogic
				.generateMethodSignature(Arrays.asList(this.array), new LeftAndRightParenthesisDto(58, 63, 0, 0)));
		assertEquals(Arrays.asList("String SIG", "String SIG2"), methodUtilsBusinessLogic
				.generateMethodSignature(Arrays.asList(this.array), new LeftAndRightParenthesisDto(68, 73, 0, 0)));

	}

	@Test
	public void testGenerateMethodReturnVal() {
		assertNull(methodUtilsBusinessLogic.generateMethodReturnVal(Arrays.asList(this.array),
				new LeftAndRightParenthesisDto(0, 0, 0, 0)));
		assertNull(methodUtilsBusinessLogic.generateMethodReturnVal(Arrays.asList(this.array),
				new LeftAndRightParenthesisDto(4, 5, 0, 0)));
		assertNull(methodUtilsBusinessLogic.generateMethodReturnVal(Arrays.asList(this.array),
				new LeftAndRightParenthesisDto(9, 11, 0, 0)));
		assertNull(methodUtilsBusinessLogic.generateMethodReturnVal(Arrays.asList(this.array),
				new LeftAndRightParenthesisDto(15, 18, 0, 0)));
		assertNull(methodUtilsBusinessLogic.generateMethodReturnVal(Arrays.asList(this.array),
				new LeftAndRightParenthesisDto(23, 28, 0, 0)));
		assertEquals("void", methodUtilsBusinessLogic.generateMethodReturnVal(Arrays.asList(this.array),
				new LeftAndRightParenthesisDto(34, 39, 0, 0)));
		assertEquals("String", methodUtilsBusinessLogic.generateMethodReturnVal(Arrays.asList(this.array),
				new LeftAndRightParenthesisDto(46, 51, 0, 0)));
		assertEquals("String", methodUtilsBusinessLogic.generateMethodReturnVal(Arrays.asList(this.array),
				new LeftAndRightParenthesisDto(58, 63, 0, 0)));
		assertEquals("void", methodUtilsBusinessLogic.generateMethodReturnVal(Arrays.asList(this.array),
				new LeftAndRightParenthesisDto(68, 73, 0, 0)));
	}

	@Test
	public void testJudgeIsReservedWordOrClassName() {

		assertTrue(methodUtilsBusinessLogic.judgeIsReservedWordOrClassName("for"));
		assertTrue(methodUtilsBusinessLogic.judgeIsReservedWordOrClassName("if"));
		assertTrue(methodUtilsBusinessLogic.judgeIsReservedWordOrClassName("CLASSNAME"));

	}

}
