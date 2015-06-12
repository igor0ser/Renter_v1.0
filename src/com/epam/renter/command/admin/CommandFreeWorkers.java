package com.epam.renter.command.admin;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.renter.controller.ICommand;
import com.epam.renter.entities.Application;
import com.epam.renter.entities.Worker;
import com.epam.renter.properties.Config;
import com.epam.renter.properties.Message;
import com.epam.renter.service.ServiceWork;
//this command show to admin list of free workers
public class CommandFreeWorkers implements ICommand {

	private static final String FORMAT = "yyyy-MM-dd'T'HH:mm";
	private static final String START = "start";
	private static final String END = "end";
	private static final String IS_CHECKED = "is_checked";
	private static final String LIST_WORKERS = "list_workers";
	private static final String LIST_WORKERS_SIZE = "list_workers_size";
	private static final String SHOW_ALL = "show-all";
	private static final String DEFAULT_START = "default_start";
	private static final String DEFAULT_END = "default_end";
	private static final String APP = "app";
	private static final String ADMIN_MESSAGE = "message";
	private final static String LAST_PAGE = "last_page";
	private final Logger logger = LogManager.getLogger(CommandCreatedApps.class
			.getName());

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		// show admin list of free workers
		SimpleDateFormat formatter = new SimpleDateFormat(FORMAT);
		String startTime = request.getParameter(START);
		String endTime = request.getParameter(END);
		boolean showAll = (request.getParameter(SHOW_ALL) != null);

		Date start = null;
		Date end = null;
		try {
			start = formatter.parse(startTime);
			end = formatter.parse(endTime);
		} catch (ParseException e) {
			logger.error(String.format(
					"Date parse error. startTime = %s, endTime = %s", startTime,
					end)+endTime);
			forvardMessage(request, response, Message.ADMIN_PARSE_DATE_ERROR);
			return null;
		}
		//start must be before end of work
		if (start.after(end)){
			logger.error(String.format(
					"Admin has chosen wrong time. Start = %s, end = %s", start,
					end));
			forvardMessage(request, response, Message.ADMIN_WRONG_TIME);
			return null;
		}

		Application app = (Application) session.getAttribute(APP);
		
		// all free workers ore free workers of only one specialty
		List<Worker> freeWorkers = (showAll) ? ServiceWork.getFreeWorkers(
				start, end) : ServiceWork.getFreeWorkers(app.getTypeOfWork(),
				start, end);

		logger.info(String.format(
				"Admin downloaded the list of free workers. List size = %d",
				freeWorkers.size()));

		response.getWriter().println(Arrays.toString(freeWorkers.toArray()));
		session.setAttribute(LIST_WORKERS, freeWorkers);
		request.setAttribute(LIST_WORKERS_SIZE, freeWorkers.size());
		// default start & end is set to calendar on page
		session.setAttribute(DEFAULT_START, startTime);
		session.setAttribute(DEFAULT_END, endTime);
		request.setAttribute(IS_CHECKED, showAll);
		request.getRequestDispatcher(
				Config.getInstance().getProperty(Config.ADMIN_HANDLE_APP))
				.forward(request, response);

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
