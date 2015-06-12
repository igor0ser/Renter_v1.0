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
import com.epam.renter.entities.Application;
import com.epam.renter.entities.Work;
import com.epam.renter.entities.Worker;

public class MySQLDAOWork implements IDAOWork {
	private static String FIND_BY_WORKER_ID = "SELECT *  FROM works WHERE idWorker=?;";
	private static String FIND_BY_APPLICATION_ID = "SELECT *  FROM works WHERE idApplication=?;";
	private static String CREATE_QUERY = "INSERT INTO works (idApplication, idWorker) VALUES (?,?);";
	private final Logger logger = LogManager.getLogger(MySQLDAOWork.class
			.getName());

	@Override
	public List<Work> findByWorker(Worker worker) {
		List<Work> list = new ArrayList<>();
		try (Connection conn = ConnectionSource.getInstance().getConnection();) {
			PreparedStatement preparedStatement = conn
					.prepareStatement(FIND_BY_WORKER_ID);
			preparedStatement.setInt(1, worker.getId());
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Application app = new Application(
						resultSet.getInt("idApplication"));
				Work work = new Work(resultSet.getInt("idWork"), app, worker);
				list.add(work);
			}
		} catch (SQLException e) {
			logger.error(e);
		}
		return list;
	}

	@Override
	public List<Work> findByApplication(Application application) {
		List<Work> list = new ArrayList<>();
		try (Connection conn = ConnectionSource.getInstance().getConnection();) {
			PreparedStatement preparedStatement = conn
					.prepareStatement(FIND_BY_APPLICATION_ID);
			preparedStatement.setInt(1, application.getId());
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Worker worker = new Worker(resultSet.getInt("idWorker"));
				Work work = new Work(resultSet.getInt("idWork"), application,
						worker);
				list.add(work);
			}
		} catch (SQLException e) {
			logger.error(e);
		}
		return list;
	}

	@Override
	public boolean create(Work work) {
		try (Connection dbConnection = ConnectionSource.getInstance()
				.getConnection();) {
			PreparedStatement preparedStatement = dbConnection
					.prepareStatement(CREATE_QUERY);
			preparedStatement.setInt(1, work.getApplication().getId());
			preparedStatement.setInt(2, work.getWorker().getId());

			preparedStatement.execute();
			return true;
		} catch (SQLException e) {
			logger.error(e);
			return false;
		}
	}

}
