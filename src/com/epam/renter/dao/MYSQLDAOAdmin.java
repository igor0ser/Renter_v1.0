package com.epam.renter.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.renter.datasource.ConnectionSource;
import com.epam.renter.entities.Admin;

public class MYSQLDAOAdmin implements IDAOAdmin {
	private static String FIND_BY_ID_QUERY = "SELECT *  FROM admins WHERE idUser=?;";
	private final Logger logger = LogManager.getLogger(MYSQLDAOAdmin.class
			.getName());

	@Override
	public Admin findByUserID(int idUser) {
		Admin admin = null;
		try (Connection conn = ConnectionSource.getInstance().getConnection();) {
			PreparedStatement preparedStatement = conn
					.prepareStatement(FIND_BY_ID_QUERY);
			preparedStatement.setInt(1, idUser);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				admin = new Admin();
				admin.setId(resultSet.getInt("idAdmin"));
				admin.getUser().setId(resultSet.getInt("idUser"));
			}
		} catch (SQLException e) {
			logger.error(e);
		}
		return admin;
	}

}
