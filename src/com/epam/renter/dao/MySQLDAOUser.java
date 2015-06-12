package com.epam.renter.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.renter.datasource.ConnectionSource;
import com.epam.renter.entities.User;

public class MySQLDAOUser implements IDAOUser {

	private static String READ_BY_ID_QUERY = "SELECT *  FROM users WHERE idUser=?;";
	private static String READ_BY_LOGIN_QUERY = "SELECT * FROM users WHERE login=?;";
	private static String CREATE_QUERY = "INSERT INTO users (login, password, name, surname, email, phoneNumber) VALUES (?,?,?,?,?,?);";
	private final Logger logger = LogManager.getLogger(MySQLDAOUser.class
			.getName());

	@Override
	public User findByID(int idUser) {
		User user = null;
		try (Connection conn = ConnectionSource.getInstance().getConnection();) {
			PreparedStatement preparedStatement = conn
					.prepareStatement(READ_BY_ID_QUERY);
			preparedStatement.setInt(1, idUser);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				user = new User();
				user.setId(resultSet.getInt("idUser"));
				user.setLogin(resultSet.getString("login"));
				user.setPassword(resultSet.getString("password"));
				user.setName(resultSet.getString("name"));
				user.setSurname(resultSet.getString("surname"));
				user.setEmail(resultSet.getString("email"));
				user.setPhoneNumber(resultSet.getString("phoneNumber"));
			}
		} catch (SQLException e) {
			logger.error(e);
		}

		return user;
	}

	@Override
	public User findByLogin(String login) {
		User user = null;
		try (Connection conn = ConnectionSource.getInstance().getConnection();) {
			PreparedStatement preparedStatement = conn
					.prepareStatement(READ_BY_LOGIN_QUERY);
			preparedStatement.setString(1, login);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				user = new User();
				user.setId(resultSet.getInt("idUser"));
				user.setLogin(resultSet.getString("login"));
				user.setPassword(resultSet.getString("password"));
				user.setName(resultSet.getString("name"));
				user.setSurname(resultSet.getString("surname"));
				user.setEmail(resultSet.getString("email"));
				user.setPhoneNumber(resultSet.getString("phoneNumber"));
			}
		} catch (SQLException e) {
			logger.error(e);
		}

		return user;
	}

	@Override
	public boolean create(User user) {
		try (Connection conn = ConnectionSource.getInstance().getConnection();) {

			PreparedStatement preparedStatement = conn
					.prepareStatement(CREATE_QUERY);
			preparedStatement.setString(1, user.getLogin());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getName());
			preparedStatement.setString(4, user.getSurname());
			preparedStatement.setString(5, user.getEmail());
			preparedStatement.setString(6, user.getPhoneNumber());
			preparedStatement.execute();
			return true;
		} catch (SQLException e) {
			logger.error(e);
			return false;
		}

	}

}
