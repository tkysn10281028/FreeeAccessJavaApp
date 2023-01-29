package com.sono.proc.sample;

import static org.junit.Assert.*;

import org.junit.Test;

public class SampleAppEntryPointTest {

	/**
	 * 引数一つもない
	 */
	@Test
	public void testValidateParamsNotEnough1() {
		String[] params = {};
		try {
			new SampleAppEntryPoint().validateParams(params);
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(e.getMessage().matches(".*Not Appropriate Number of Parameters."));
		}
	}

	/**
	 * 引数1つしかない
	 */
	@Test
	public void testValidateParamsNotEnough2() {
		String[] params = { "1" };
		try {
			new SampleAppEntryPoint().validateParams(params);
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(e.getMessage().matches(".*Not Appropriate Number of Parameters."));
		}
	}

	/**
	 * 引数2つしかない
	 */
	@Test
	public void testValidateParamsNotEnough3() {
		String[] params = { "1", "2" };
		try {
			new SampleAppEntryPoint().validateParams(params);
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(e.getMessage().matches(".*Not Appropriate Number of Parameters."));
		}
	}

	/**
	 * 引数が4つある
	 */
	@Test
	public void testValidateParamsTooMany() {
		String[] params = { "1", "2", "3", "4" };
		try {
			new SampleAppEntryPoint().validateParams(params);
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(e.getMessage().matches(".*Not Appropriate Number of Parameters."));
		}
	}

	/**
	 * 認可コードのみ存在
	 */
	@Test
	public void testValidateParamsPartialMatches1() {
		String[] params = { "", "", "code=CODE" };
		try {
			new SampleAppEntryPoint().validateParams(params);
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(e.getMessage().matches(".*client_id,client_secret not found."));
		}
	}

	/**
	 * client_idのみ存在
	 */
	@Test
	public void testValidateParamsPartialMatches2() {
		String[] params = { "client_id=CLIENT_ID", "", "" };
		try {
			new SampleAppEntryPoint().validateParams(params);
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(e.getMessage().matches(".*authorization code,client_secret not found."));
		}
	}

	/**
	 * client_secretのみ存在
	 */
	@Test
	public void testValidateParamsPartialMatches3() {
		String[] params = { "", "client_secret=CLIENT_SECRET", "" };
		try {
			new SampleAppEntryPoint().validateParams(params);
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(e.getMessage().matches(".*authorization code,client_id not found."));
		}
	}

	/**
	 * 認可コード及びclient_idのみ
	 */
	@Test
	public void testValidateParamsPartialMatches4() {
		String[] params = { "client_id=CLIENT_ID", "code=CODE", "" };
		try {
			new SampleAppEntryPoint().validateParams(params);
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(e.getMessage().matches(".*client_secret not found."));
		}
	}

	/**
	 * 認可コード及びclient_secretのみ
	 */
	@Test
	public void testValidateParamsPartialMatches5() {
		String[] params = { "", "client_secret=CLIENT_SECRET,code=CODE" };
		try {
			new SampleAppEntryPoint().validateParams(params);
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(e.getMessage().matches(".*client_id not found."));
		}
	}

	/**
	 * client_id及びclient_secretのみ
	 */
	@Test
	public void testValidateParamsPartialMatches6() {
		String[] params = { "client_id=CLIENT_ID", "", "client_secret=CLIENT_SECRET" };
		try {
			new SampleAppEntryPoint().validateParams(params);
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(e.getMessage().matches(".*authorization code not found."));
		}
	}

	/**
	 * 全部いない
	 */
	@Test
	public void testValidateParamsPartialMatches7() {
		String[] params = { "", "", "" };
		try {
			new SampleAppEntryPoint().validateParams(params);
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(e.getMessage().matches(".*authorization code,client_id,client_secret not found."));
		}
	}

	/**
	 * 正常系
	 */
	@Test
	public void testValidateParams() {
		String[] params = { "client_id=CLIENT_ID", "client_secret=CLIENT_SECRET", "code=CODE" };
		var result = new SampleAppEntryPoint().validateParams(params);
		if (result == null) {
			fail();
		}
		assertEquals(result.getAuthorizationCode(), "CODE");
		assertEquals(result.getClientId(), "CLIENT_ID");
		assertEquals(result.getClientSecret(), "CLIENT_SECRET");
	}
}
