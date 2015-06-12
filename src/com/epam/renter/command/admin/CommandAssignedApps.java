package com.epam.renter.command.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.renter.controller.ICommand;
import com.epam.renter.datasource.DAOFactory;
import com.epam.renter.entities.Address;
import com.epam.renter.entities.Application;
import com.epam.renter.entities.Status;
import com.epam.renter.entities.User;
import com.epam.renter.entities.Worker;
import com.epam.renter.properties.Config;
import com.epam.renter.service.ServiceWork;

// this command shows to admin list of all assigned and not completed applications
public class CommandAssignedApps implements ICommand {

	private static final String LIST = "list";
	private static final String LIST_SIZE = "list_size";
	private final static String LAST_PAGE = "last_page";
	private final Logger logger = LogManager.getLogger(CommandCreatedApps.class
			.getName());

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		// list of assigned applications from db
		List<Application> list = DAOFactory.mySQLFactory.mySQLDAOApplication
				.findByStatus(Status.ASSIGNED);
		for (Application app : list) {
			User user = DAOFactory.mySQLFactory.mySQLDAOUser.findByID(app
					.getUser().getId());
			Address address = DAOFactory.mySQLFactory.mySQLDAOAddress
					.findByUser(user);
			user.setAddress(address);
			app.setUser(user);
			// list of workers to each application
			List<Worker> workers = ServiceWork.getWorkersByApp(app);
			app.setWorkers(workers);

		}
		logger.info(String.format(
				"Admin downloaded the list of unsigned apps. List size = %d",
				list.size()));
		session.setAttribute(LIST, list);
		session.setAttribute(LIST_SIZE, list.size());
		session.setAttribute(LAST_PAGE,
				Config.getInstance().getProperty(Config.ADMIN_ASSIGNED_APPS));
		request.getRequestDispatcher(
				Config.getInstance().getProperty(Config.ADMIN_ASSIGNED_APPS))
				.forward(request, response);

		return null;
	}

}
