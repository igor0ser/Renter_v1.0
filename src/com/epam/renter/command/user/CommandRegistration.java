package com.epam.renter.command.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.renter.controller.ICommand;
import com.epam.renter.datasource.DAOFactory;
import com.epam.renter.entities.Address;
import com.epam.renter.entities.User;
import com.epam.renter.properties.Config;
import com.epam.renter.properties.Message;

public class CommandRegistration implements ICommand {
	private static final String LOGIN = "login";
	private static final String PASSWORD = "password";
	private static final String REPEAT_PASSWORD = "repeat_password";
	private static final String NAME = "name";
	private static final String SURNAME = "surname";
	private static final String EMAIL = "email";
	private static final String PHONE_NUMBER = "phone_number";
	private static final String STREET = "street";
	private static final String HOUSE = "house";
	private static final String APPARTMENT = "appartment";
	private static final String USER_ID = "user_id";
	private static final String ERROR = "error";
	private final static String LAST_PAGE = "last_page";
	private final Logger logger = LogManager
			.getLogger(CommandRegistration.class.getName());

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String login = request.getParameter(LOGIN);
		String password = request.getParameter(PASSWORD);
		String repeatPassword = request.getParameter(REPEAT_PASSWORD);

		// "password" must equals to "repeat password"
		if (!password.equals(repeatPassword)) {
			logger.info(String
					.format("Password doesn't equal to repeated one. Password= %s, repeated password = %s ",
							password, repeatPassword));
			session.setAttribute(LAST_PAGE,
					Config.getInstance().getProperty(Config.ERROR_LOGIN));
			session.setAttribute(
					ERROR,
					Message.getInstance().getProperty(
							Message.WRONG_REP_PASSWORD));
			request.getRequestDispatcher(
					Config.getInstance().getProperty(Config.ERROR_LOGIN))
					.forward(request, response);
			return null;
		}

		if (DAOFactory.mySQLFactory.mySQLDAOUser.findByLogin(login) != null) {
			// if this Login is already in use:
			logger.info(String.format("Login is already in use. Login = %s ",
					login));
			request.getSession()
					.setAttribute(
							ERROR,
							Message.getInstance().getProperty(
									Message.WRONG_LOGIN_USED));
			session.setAttribute(LAST_PAGE,
					Config.getInstance().getProperty(Config.ERROR_LOGIN));
			request.getRequestDispatcher(
					Config.getInstance().getProperty(Config.ERROR_LOGIN))
					.forward(request, response);
			return null;
		}

		String name = request.getParameter(NAME);
		String surname = request.getParameter(SURNAME);
		String email = request.getParameter(EMAIL);

		response.getWriter().println(surname);
		String phoneNumber = request.getParameter(PHONE_NUMBER);
		String street = request.getParameter(STREET);
		String houseNumber = request.getParameter(HOUSE);
		String appartmentNumber = request.getParameter(APPARTMENT);

		User user = new User(login, password, name, surname, email, phoneNumber);
		if (DAOFactory.mySQLFactory.mySQLDAOUser.create(user)) {
			// new User is created
			user = DAOFactory.mySQLFactory.mySQLDAOUser.findByLogin(login);
			Address address = new Address(street, houseNumber,
					appartmentNumber, user);
			//creating address of this user and loading it in DB
			if (DAOFactory.mySQLFactory.mySQLDAOAddress.create(address)){
				logger.error(String.format("Error in creating new address in DB"));
			};
			session.setAttribute(LOGIN, login);
			session.setAttribute(USER_ID, user.getId());
			session.setAttribute(LAST_PAGE,
					Config.getInstance().getProperty(Config.WELCOME));
			logger.info(String.format("User pass threw registration. User login = %s",
					login));
			request.getRequestDispatcher(
					Config.getInstance().getProperty(Config.WELCOME)).forward(
					request, response);
		} else {
			//some errors in DB
			logger.error(String.format("Error in creating new user in DB"));
			request.setAttribute(ERROR,
					Message.getInstance().getProperty(Message.RANDOM_ERROR));
			session.setAttribute(LAST_PAGE,
					Config.getInstance().getProperty(Config.ERROR_LOGIN));
			request.getRequestDispatcher(
					Config.getInstance().getProperty(Config.ERROR_LOGIN))
					.forward(request, response);
			}

		return null;
	}
}
