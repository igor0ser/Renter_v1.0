package com.epam.renter.command.user;

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
import com.epam.renter.entities.Application;
import com.epam.renter.properties.Config;

public class CommandMyApplications implements ICommand {

	private static final String USER_ID = "user_id";
	private static final String LIST = "list";
	private static final String LIST_SIZE = "list_size";
	private final static String LAST_PAGE = "last_page";
	private final Logger logger = LogManager.getLogger(CommandMyApplications.class.getName());

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int userID = (int) session.getAttribute(USER_ID);
		
		//getting list of application that created by this user
		List<Application> list = DAOFactory.mySQLFactory.mySQLDAOApplication
				.findByUserID(userID);

		session.setAttribute(LIST, list);
		session.setAttribute(LIST_SIZE, list.size());
		logger.info(String.format("User (id = %d) watches his applications. He has %d applications", userID, list.size()));
		session.setAttribute(LAST_PAGE,
				Config.getInstance().getProperty(Config.MY_APPS));
		request.getRequestDispatcher(Config.getInstance().getProperty(Config.MY_APPS)).forward(request,
				response);

		return null;
	}

}
