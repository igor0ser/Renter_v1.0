package com.epam.renter.command.admin;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.epam.renter.entities.Status;
import com.epam.renter.entities.Work;
import com.epam.renter.entities.Worker;
import com.epam.renter.properties.Config;
import com.epam.renter.properties.Message;

// this command assign workers to application
public class CommandAddWorkers implements ICommand {

	private static final String FORMAT = "yyyy-MM-dd'T'HH:mm";
	private static final String DEFAULT_START = "default_start";
	private static final String DEFAULT_END = "default_end";
	private static final String APP = "app";
	private static final String LIST_WORKERS = "list_workers";
	private static final String ADMIN_MESSAGE = "message";
	private final static String LAST_PAGE = "last_page";

	private final Logger logger = LogManager.getLogger(CommandCreatedApps.class
			.getName());

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Application app = (Application) session.getAttribute(APP);
		SimpleDateFormat formatter = new SimpleDateFormat(FORMAT);
		String startTime = (String) session.getAttribute(
				DEFAULT_START);
		String endTime = (String) session.getAttribute(DEFAULT_END);
		Date start = null;
		Date end = null;
		// parsing dates
		try {
			start = formatter.parse(startTime);
			end = formatter.parse(endTime);
		} catch (ParseException e) {
			logger.error(String.format(
					"Date parse error. startTime = %s, endTime = %s", start,
					end)
					+ e);
			forvardMessage(request, response, Message.ADMIN_PARSE_DATE_ERROR);
			return null;

		}

		// getting list of free workers (from previous command)
		List<Worker> list_worker = (List<Worker>) session.getAttribute(LIST_WORKERS);
		System.out.println(list_worker.size());
		int quantity = 0;
		for (Worker worker : list_worker) {
			// looking for checked workers
			if (request.getParameter(String.valueOf(worker.getId())) != null) {
				// saving worker in this application work
				boolean saved = DAOFactory.mySQLFactory.mySQLDAOWork
						.create(new Work(app, worker));
				// if errors in saving - logging it
				if (!saved) {
					logger.error(String.format(
							"Work wasn't saved to DB. Worker = %s ", worker));
				}
				quantity++;
			}

			// if admin has chosen 0 workers - error
			if (quantity == 0) {
				logger.info("Admin has chosen 0 workers.");
				forvardMessage(request, response, Message.ADMIN_NULL_WORKERS);
				return null;
			}

			// changing application to assigned
			app.setStart(start);
			app.setEnd(end);
			app.setStatus(Status.ASSIGNED);
			// changing application to assigned in DB
			boolean isAppSaved = DAOFactory.mySQLFactory.mySQLDAOApplication
					.update(app);

			// if error - show message about error

			if (!isAppSaved) {
				logger.error(String.format(
						"Application wasn't saved to DB. Application = %s ",
						app));
				forvardMessage(request, response, Message.ADMIN_DB_ERROR);
				return null;
			}

			logger.info(String.format(
					"Application was assigned. Application = %s", app));

		}
		forvardMessage(request, response, Message.ADMIN_APP_IS_ASSIGNED);
		return null;
	}

	private void forvardMessage(HttpServletRequest request,
			HttpServletResponse response, String messageKey)
			throws ServletException, IOException {
		request.getSession().setAttribute(ADMIN_MESSAGE, messageKey);
		request.getSession().setAttribute(LAST_PAGE,
				Config.getInstance().getProperty(Config.ADMIN_MESSAGE));
		request.getRequestDispatcher(
				Config.getInstance().getProperty(Config.ADMIN_MESSAGE))
				.forward(request, response);
	}

}
