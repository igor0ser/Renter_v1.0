package com.epam.renter.command.admin;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.renter.controller.ICommand;
import com.epam.renter.properties.Config;
import com.epam.renter.service.ServiceWork;
import com.epam.renter.service.WorkJS;

// this command assign workers to application
public class CommandWorkplan implements ICommand {

	private final Logger logger = LogManager.getLogger(CommandWorkplan.class
			.getName());
	private final String DAY = "day";
	private static final String FORMAT = "yyyy-MM-dd";
	private final static String LAST_PAGE = "last_page";
	 

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		SimpleDateFormat formatter = new SimpleDateFormat(FORMAT);
		HttpSession session = request.getSession();
		String day = request.getParameter(DAY);
		
		Calendar calendar = setStart(day);
		
		Date start = calendar.getTime();
		calendar.add(Calendar.HOUR_OF_DAY, 12);
		Date end = calendar.getTime();
		
		String newDay = formatter.format(start);
		
		List<WorkJS> workplan = ServiceWork.getWorkplan(start, end, newDay);
		logger.info("User loaded a workplan");
		session.setAttribute("workplan", workplan);
		session.setAttribute(DAY, newDay);
		session.setAttribute(LAST_PAGE,
				Config.getInstance().getProperty(Config.ADMIN_WORKPLAN));
		request.getRequestDispatcher(Config.getInstance().getProperty(Config.ADMIN_WORKPLAN)).forward(request, response);
		return null;
	}

	private Calendar setStart(String day) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat(FORMAT);
		if (day != null) {
			try {
				Date d = formatter.parse(day);
				calendar.setTime(d);
			} catch (ParseException e) {
				logger.error(String.format("Date parse error. Day = %s", day)
						+ e);
			}
		}

		calendar.set(Calendar.HOUR_OF_DAY, 8);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar;
		
	}
}
