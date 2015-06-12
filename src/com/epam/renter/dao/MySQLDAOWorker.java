package com.epam.renter.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.renter.datasource.ConnectionSource;
import com.epam.renter.entities.TypeOfWork;
import com.epam.renter.entities.Worker;

public class MySQLDAOWorker implements IDAOWorker {
	private static String FIND_BY_ID_TYPE_OF_WORK = "SELECT *  FROM workers WHERE typeOfWork=?;";
	private static String FIND_BY_ID_QUERY = "SELECT *  FROM workers WHERE idWorker=?;";
	private static String READ_ALL_QUERY = "SELECT *  FROM workers;";
	private final Logger logger = LogManager.getLogger(MySQLDAOWorker.class
			.getName());

	@Override
	public List<Worker> findByTypeOfWork(TypeOfWork typeOfWork) {
		List<Worker> list = new ArrayList<>();
		try (Connection conn = ConnectionSource.getInstance().getConnection();) {
			PreparedStatement preparedStatement = conn
					.prepareStatement(FIND_BY_ID_TYPE_OF_WORK);
			preparedStatement.setString(1, typeOfWork.toString());
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Worker worker = new Worker();
				worker.setId(resultSet.getInt("idWorker"));
				worker.setName(resultSet.getString("name"));
				worker.setSurname(resultSet.getString("surname"));
				worker.setTypeOfWork(TypeOfWork.valueOf(resultSet
						.getString("typeOfWork")));
				list.add(worker);
			}
		} catch (SQLException e) {
			logger.error(e);
		}
		return list;
	}

	@Override
	public List<Worker> readAll() {
		List<Worker> list = new ArrayList<>();
		try (Connection conn = ConnectionSource.getInstance().getConnection();) {
			PreparedStatement preparedStatement = conn
					.prepareStatement(READ_ALL_QUERY);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Worker worker = new Worker();
				worker.setId(resultSet.getInt("idWorker"));
				worker.setName(resultSet.getString("name"));
				worker.setSurname(resultSet.getString("surname"));
				worker.setTypeOfWork(TypeOfWork.valueOf(resultSet
						.getString("typeOfWork")));
				list.add(worker);
			}
		} catch (SQLException e) {
			logger.error(e);
		}
		return list;
	}

	@Override
	public Worker findByID(int id) {
		Worker worker = null;
		try (Connection conn = ConnectionSource.getInstance().getConnection();) {
			PreparedStatement preparedStatement = conn
					.prepareStatement(FIND_BY_ID_QUERY);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				worker = new Worker();
				worker.setId(resultSet.getInt("idWorker"));
				worker.setName(resultSet.getString("name"));
				worker.setSurname(resultSet.getString("surname"));
				worker.setTypeOfWork(TypeOfWork.valueOf(resultSet
						.getString("typeOfWork")));

			}
		} catch (SQLException e) {
			logger.error(e);
		}

		return worker;
	}

}
