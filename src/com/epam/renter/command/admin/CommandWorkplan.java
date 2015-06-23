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
import com.epam.renter.service.ServiceWork;
import com.epam.renter.service.WorkUnit;

// this command assign workers to application
public class CommandWorkplan implements ICommand {

	private final Logger logger = LogManager.getLogger(CommandWorkplan.class
			.getName());
	private final String DAY = "day";
	private static final String FORMAT = "yyyy-MM-dd";
	private Date start;
	private Date end;

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String day = request.getParameter(DAY);
		System.out.println(day);
		day = setDates(day);
		List<WorkUnit> workplan = ServiceWork.getWorkplan(start, end, day);
		workplan.get(0).setName(day);
		logger.info("User loaded a workplan");
		session.setAttribute("workplan", workplan);
		session.setAttribute(DAY, day);
		request.getRequestDispatcher("/WEB-INF/jsp_admin/workplan.jsp")
				.forward(request, response);
		return null;
	}

	private String setDates(String day) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat(FORMAT);
		String result = null;
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
		start = calendar.getTime();
		result = formatter.format(start);

		System.out.println("start = " + start);

		calendar.add(Calendar.HOUR_OF_DAY, 12);
		end = calendar.getTime();
		System.out.println("end = " + end);
		return result;
	}
}
