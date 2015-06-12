package com.epam.renter.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.renter.datasource.ConnectionSource;
import com.epam.renter.entities.Address;
import com.epam.renter.entities.User;

public class MySQLDAOAddress implements IDAOAddress {
	private static String READ_BY_ID_QUERY = "SELECT * FROM addresses WHERE idUser=?;";
	private static String CREATE_QUERY = "INSERT INTO addresses (street, house, appartment, idUser) VALUES (?,?,?,?);";
	private final Logger logger = LogManager.getLogger(MySQLDAOAddress.class
			.getName());
	@Override
	public Address findByUser(User user) {
		Address address = null;
		try (Connection conn = ConnectionSource.getInstance().getConnection();) {
			PreparedStatement preparedStatement = conn
					.prepareStatement(READ_BY_ID_QUERY);
			preparedStatement.setInt(1, user.getId());
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				address = new Address();
				address.setId(resultSet.getInt("idAddress"));
				address.setStreet(resultSet.getString("street"));
				address.setHouse(resultSet.getString("house"));
				address.setAppartment(resultSet.getString("appartment"));
				address.setUser(user);
			}
		} catch (SQLException e) {
			logger.error(e);
		}
		return address;
	}

	@Override
	public boolean create(Address address) {
		try (Connection dbConnection = ConnectionSource.getInstance()
				.getConnection();) {
			PreparedStatement preparedStatement = dbConnection
					.prepareStatement(CREATE_QUERY);
			preparedStatement.setString(1, address.getStreet());
			preparedStatement.setString(2, address.getHouse());
			preparedStatement.setString(3, address.getAppartment());
			preparedStatement.setInt(4, address.getUser().getId());
			preparedStatement.execute();
			return true;
		} catch (SQLException e) {
			logger.error(e);
			return false;
		}

	}

}
