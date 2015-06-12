package com.epam.renter.command.user;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.renter.controller.ICommand;
import com.epam.renter.datasource.DAOFactory;
import com.epam.renter.entities.Application;
import com.epam.renter.entities.TypeOfWork;
import com.epam.renter.entities.User;
import com.epam.renter.properties.Config;

//coomand that allows user leave his applications
public class CommandCreateApp implements ICommand {

	private static final String USER_ID = "user_id";
	private static final String ABOUT = "about";
	private static final String TYPE = "type_of_work";
	private static final String DESIRABLE = "desirable";
	private static final String FORMAT = "yyyy-MM-dd'T'HH:mm";
	private final static String LAST_PAGE = "last_page";
	private final Logger logger = LogManager.getLogger(CommandCreateApp.class
			.getName());

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		SimpleDateFormat formatter = new SimpleDateFormat(FORMAT);

		// parsing application from browser
		String about = request.getParameter(ABOUT);
		String type = request.getParameter(TYPE);
		TypeOfWork typeOfWork = TypeOfWork.valueOf(type);
		String desirableTime = request.getParameter(DESIRABLE);
		int userID = (int) session.getAttribute(USER_ID);
		Date desirable = null;
		try {
			// parse Date from browser
			desirable = formatter.parse(desirableTime);
		} catch (ParseException e) {
			logger.error(String.format(
					"Error in Date parsing. String date = %s", desirableTime)
					+ e);
		}

		User user = new User(userID);
		Application application = new Application(user, about, typeOfWork,
				desirable);

		// trying to add new Application to DB. flag returns true if all is OK with it
		boolean flag = DAOFactory.mySQLFactory.mySQLDAOApplication
				.create(application);

		if (flag) {
			logger.info(String.format("Application is loaded in DB. App = %s",
					application));
			session.setAttribute(LAST_PAGE,
					Config.getInstance().getProperty(Config.THANK_YOU));
			request.getRequestDispatcher(
					Config.getInstance().getProperty(Config.THANK_YOU))
					.forward(request, response);
		} else {
			logger.error(String.format(
					"Some error in loading application in DB. App = %s",
					application));
			session.setAttribute(LAST_PAGE,
					Config.getInstance().getProperty(Config.ERROR_DB));
			request.getRequestDispatcher(
					Config.getInstance().getProperty(Config.ERROR_DB)).forward(
					request, response);
		}

		return null;
	}

}
