package com.epam.renter.command.user;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.renter.controller.ICommand;
import com.epam.renter.datasource.DAOFactory;
import com.epam.renter.entities.User;
import com.epam.renter.mail.Mailer;
import com.epam.renter.properties.Config;

public class CommandSendPassword implements ICommand {

	private static final String EMAIL = "email";
	private static final String ERROR = "error";
	private static final String WRONG_EMAIL = "Wrong_email";
	private static final String WAS_SENT = "Was_sent";
	private final static String LAST_PAGE = "last_page";
	private final static String SUBJECT = "Your password&login from Renter";
	private final Logger logger = LogManager
			.getLogger(CommandSendPassword.class.getName());

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter(EMAIL);
		User user = DAOFactory.mySQLFactory.mySQLDAOUser.findByEmail(email);

		if (user == null) {
			forvardMessage(request, response, WRONG_EMAIL, Config.getInstance()
					.getProperty(Config.ERROR_LOGIN));
			logger.info(String.format(
					"Email isn't found in our database. Email = %s", email));
			return null;
		}
		Mailer mailer = new Mailer();
		try {
			String message = "Your login is " + user.getLogin()
					+ "\nYour password is " + user.getPassword();
			mailer.sendEmail(email, SUBJECT, message);
			logger.info(String.format("Password sent to email = %s", email));
			forvardMessage(request, response, WAS_SENT, Config.getInstance()
					.getProperty(Config.WAS_SENT));

			return null;
		} catch (MessagingException e) {
			forvardMessage(request, response, WRONG_EMAIL, Config.getInstance()
					.getProperty(Config.ERROR_LOGIN));

			logger.error(String.format("Error in sending email. Email = %s",
					email) + e);
			return null;
		}

	}

	private void forvardMessage(HttpServletRequest request,
			HttpServletResponse response, String messageKey, String page)
			throws ServletException, IOException {
		request.getSession().setAttribute(ERROR, messageKey);
		request.getSession().setAttribute(LAST_PAGE, page);
		request.getRequestDispatcher(page).forward(request, response);
	}
}
