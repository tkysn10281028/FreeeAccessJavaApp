package com.sono.proc.sample.database.logic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sono.proc.common.interfaces.BusinessLogicCommon;
import com.sono.proc.common.utils.LogUtils;
import com.sono.proc.sample.database.dto.ClientInfoDatabaseDto;
import com.sono.proc.sample.dto.MainProcParameterDto;

@Component
public class ClientInfoDatabaseBusinessLogic implements BusinessLogicCommon {
	@Autowired
	LogUtils logUtils;
	static final String JDBC_DRIVER = "org.h2.Driver";
	static final String URL = "jdbc:h2:~/test";
	static final String USER = "sa";
	static final String PASS = "";
	String createSql = "CREATE TABLE IF NOT EXISTS client_info (ID"
			+ " INT PRIMARY KEY AUTO_INCREMENT,authorization_code VARCHAR(255),"
			+ " client_id VARCHAR(255), client_secret VARCHAR(255))";
	String insertSqlFirst = "INSERT INTO CLIENT_INFO (AUTHORIZATION_CODE ,CLIENT_ID ,CLIENT_SECRET)VALUES('";
	String insertSqlMiddle = "','";
	String insertSqlEnd = "');";
	String updateSqlAuthCode = "UPDATE CLIENT_INFO SET AUTHORIZATION_CODE ='";
	String updateSqlClientId = "',CLIENT_ID ='";
	String updateSqlClientSecret = "',CLIENT_SECRET ='";
	String updateSqlFinal = "WHERE ID=1;";
	String selectSql = "select * from client_info where ID=1 limit 1";
	private Connection conn = null;
	private Statement stmt = null;
	private String authorizationCode;
	private String clientId;
	private String clientSecret;
	private ClientInfoDatabaseDto outputDto;

	public ClientInfoDatabaseDto execute(MainProcParameterDto dto) {
		this.authorizationCode = dto.getAuthorizationCode();
		this.clientId = dto.getClientId();
		this.clientSecret = dto.getClientSecret();
		executeBeforeProcess();
		executeMainProcess();
		executeAfterProcess();
		return this.outputDto;
	}

	@Override
	public void executeBeforeProcess() {
		logUtils.printMethod();
		try {
			Class.forName(JDBC_DRIVER);
			this.conn = DriverManager.getConnection(URL, USER, PASS);
			this.stmt = this.conn.createStatement();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void executeMainProcess() {
		logUtils.printMethod();
		try {
			this.stmt.execute(createSql);
			var result = findData();
			logUtils.printDto(result);
			boolean isResultEmpty = StringUtils.isEmpty(result.getAuthorizationCode())
					&& StringUtils.isEmpty(result.getAuthorizationCode())
					&& StringUtils.isEmpty(result.getAuthorizationCode());
			if (isResultEmpty) {
				this.stmt.execute(insertSqlFirst + this.authorizationCode + insertSqlMiddle + this.clientId
						+ insertSqlMiddle + this.clientSecret + insertSqlEnd);
				result = findData();
				if (isResultEmpty) {
					throw new RuntimeException("DataBase Error!");
				} else {
					this.outputDto = result;
				}
			} else {
				this.outputDto = result;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public ClientInfoDatabaseDto findData() throws SQLException {
		var result = this.stmt.executeQuery(selectSql);
		var outDto = new ClientInfoDatabaseDto();
		while (result.next()) {
			outDto.setAuthorizationCode(result.getString("AUTHORIZATION_CODE"));
			outDto.setClientId(result.getString("CLIENT_ID"));
			outDto.setClientSecret(result.getString("CLIENT_SECRET"));
		}
		return outDto;
	}

	@Override
	public void executeAfterProcess() {
		logUtils.printMethod();
		try {
			if (this.stmt != null) {
				this.stmt.close();
			}
			if (this.conn != null) {
				this.conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
