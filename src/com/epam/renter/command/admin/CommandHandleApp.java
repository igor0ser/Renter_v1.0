package com.epam.renter.command.admin;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
import com.epam.renter.entities.User;
import com.epam.renter.properties.Config;
// this command allows admin handle one application
public class CommandHandleApp implements ICommand {

	private static final String APP_ID = "app_id";
	private static final String APP = "app";
	private static final String DEFAULT_START = "default_start";
	private static final String DEFAULT_END = "default_end";
	private static final String FORMAT = "yyyy-MM-dd'T'HH:mm";
	private final static String LAST_PAGE = "last_page";
	private final Logger logger = LogManager.getLogger(CommandCreatedApps.class
			.getName());

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		SimpleDateFormat formatter = new SimpleDateFormat(FORMAT);
		String app_id = request.getParameter(APP_ID);
		int id = Integer.parseInt(app_id);
		Application app = DAOFactory.mySQLFactory.mySQLDAOApplication
				.findByID(id);
		User user = DAOFactory.mySQLFactory.mySQLDAOUser.findByID(app.getUser()
				.getId());
		Address address = DAOFactory.mySQLFactory.mySQLDAOAddress
				.findByUser(user);
		user.setAddress(address);
		app.setUser(user);

		//default start equals to desirable time, default end equals to desirable time + 2 hours
		Calendar cal = Calendar.getInstance();
		cal.setTime(app.getDesirable());
		cal.add(Calendar.HOUR, 2);
		Date defaultEndTime = cal.getTime();
		String defaultStart = formatter.format(app.getDesirable());
		String defaultEnd = formatter.format(defaultEndTime);
		logger.info(String.format(
				"Admin goes to application's handling page. App id = %d", id));
		// default start & end is set to calendar on page
		session.setAttribute(DEFAULT_START, defaultStart);
		session.setAttribute(DEFAULT_END, defaultEnd);
		session.setAttribute(APP, app);
		session.setAttribute(LAST_PAGE,
				Config.getInstance().getProperty(Config.ADMIN_HANDLE_APP));
		request.getRequestDispatcher(
				Config.getInstance().getProperty(Config.ADMIN_HANDLE_APP))
				.forward(request, response);
		return null;
	}
}
