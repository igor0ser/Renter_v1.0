package com.epam.renter.datasource;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectionSource {

	private InitialContext initContext;
	private DataSource ds;
	private static ConnectionSource instance = new ConnectionSource();
	private final Logger logger = LogManager.getLogger(ConnectionSource.class
			.getName());
	
	private ConnectionSource() {
		try {
			initContext = new InitialContext();
			ds = (DataSource) initContext.lookup("java:comp/env/jdbc/renterdb");
		} catch (NamingException e) {
			logger.error(e);
		}

	}

	public static ConnectionSource getInstance() {
		return instance;
	}

	public Connection getConnection() {
		Connection connection = null;
		try {
			connection = ds.getConnection();
		} catch (SQLException e) {
			logger.error(e);
		}
		return connection;
	}
}
