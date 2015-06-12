package com.epam.renter.command.admin;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.renter.controller.ICommand;
import com.epam.renter.datasource.DAOFactory;
import com.epam.renter.entities.Application;
import com.epam.renter.entities.Status;
import com.epam.renter.properties.Config;
import com.epam.renter.properties.Message;

// this command changes application's status from ASSIGNED to COMPLETE
public class CommandSetAppComplete implements ICommand {

	private static final String APP_ID = "app_id";
	private static final String ADMIN_MESSAGE = "message";
	private final static String LAST_PAGE = "last_page";
	private final Logger logger = LogManager.getLogger(CommandCreatedApps.class
			.getName());

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Locale locale = request.getLocale();
		Locale.setDefault(locale);
		// loading an application from DB
		String id = request.getParameter(APP_ID);
		int appId = Integer.parseInt(id);
		Application app = DAOFactory.mySQLFactory.mySQLDAOApplication
				.findByID(appId);
		// changing it's status
		app.setStatus(Status.COMPLETED);
		// changing it in DB
		boolean flag = DAOFactory.mySQLFactory.mySQLDAOApplication.update(app);
		if (!flag) {
			session.setAttribute(ADMIN_MESSAGE,
					Message.ADMIN_ERROR);
			logger.error(String.format(
					"Application wasn't saved to DB. Application = %s ", app));

		} else {
			logger.info(String.format(
					"Application saved to DB. Application = %s ", app));
			session.setAttribute(ADMIN_MESSAGE, Message.ADMIN_APP_IS_COMPLETED);

		}
		session.setAttribute(LAST_PAGE,
				Config.getInstance().getProperty(Config.ADMIN_MESSAGE));
		request.getRequestDispatcher(
				Config.getInstance().getProperty(Config.ADMIN_MESSAGE))
				.forward(request, response);
		return null;
	}
}
