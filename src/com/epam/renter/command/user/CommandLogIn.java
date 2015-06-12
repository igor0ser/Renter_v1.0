package com.epam.renter.command.user;

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
import com.epam.renter.entities.Admin;
import com.epam.renter.entities.User;
import com.epam.renter.properties.Config;
import com.epam.renter.properties.Message;

public class CommandLogIn implements ICommand {

	private static final String LOGIN = "login";
	private static final String PASSWORD = "password";
	private static final String USER_ID = "user_id";
	private static final String ERROR = "error";
	private static final String ADMIN_MESSAGE = "message";
	private final static String LAST_PAGE = "last_page";
	private final Logger logger = LogManager.getLogger(CommandLogIn.class.getName());

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String login = request.getParameter(LOGIN);
		String password = request.getParameter(PASSWORD);
		Locale locale = request.getLocale();
		Locale.setDefault(locale);
		User user = DAOFactory.mySQLFactory.mySQLDAOUser.findByLogin(login);

		// login is not found in DB
		if (user == null) {
			logger.info(String.format("Wrong login. Login=%s", login));
			session.setAttribute(ERROR,
					Message.WRONG_LOGIN);
			session.setAttribute(LAST_PAGE,
					Config.getInstance().getProperty(Config.ERROR_LOGIN));
			request.getRequestDispatcher(
					Config.getInstance().getProperty(Config.ERROR_LOGIN))
					.forward(request, response);
			// login is OK, but password is wrong
		} else if (!user.getPassword().equals(password)) {
			logger.info(String.format("Wrong password. Login=%s, Password=%s",
					login, password));
			session.setAttribute(ERROR,
					Message.WRONG_PASSWORD);
			session.setAttribute(LAST_PAGE,
					Config.getInstance().getProperty(Config.ERROR_LOGIN));
			request.getRequestDispatcher(
					Config.getInstance().getProperty(Config.ERROR_LOGIN))
					.forward(request, response);
		}
		// login is OK, password is OK
		else {
			Admin admin = DAOFactory.mySQLFactory.MYSQLDAOAdmin
					.findByUserID(user.getId());

			if (admin != null) {
				// login belongs to administrator's account
				logger.info(String.format("Admin has logged in. Login=%s",
						login));
				session.setAttribute(LOGIN, login);
				session.setAttribute(
						ADMIN_MESSAGE,
						Message.ADMIN_WELCOME);
				session.setAttribute(LAST_PAGE,
						Config.getInstance().getProperty(Config.ADMIN_MESSAGE));
				request.getRequestDispatcher(
						Config.getInstance().getProperty(Config.ADMIN_MESSAGE))
						.forward(request, response);
			} else {
				// login belongs to user's account
				logger.info(String
						.format("User has logged in. Login=%s", login));

				session.setAttribute(LOGIN, login);
				session.setAttribute(USER_ID, user.getId());
				session.setAttribute(LAST_PAGE,
						Config.getInstance().getProperty(Config.WELCOME));
				request.getRequestDispatcher(
						Config.getInstance().getProperty(Config.WELCOME))
						.forward(request, response);
			}

		}
		return null;
	}
}
