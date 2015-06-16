package com.epam.renter.command.admin;

import java.io.IOException;
import java.sql.Timestamp;
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
import com.epam.renter.datasource.DAOFactory;
import com.epam.renter.entities.Application;
import com.epam.renter.entities.Status;
import com.epam.renter.entities.Work;
import com.epam.renter.entities.Worker;
import com.epam.renter.properties.Config;
import com.epam.renter.properties.Message;
import com.epam.renter.service.ServiceWork;

// this command assign workers to application
public class CommandWorkplan implements ICommand {

	private final Logger logger = LogManager.getLogger(CommandWorkplan.class
			.getName());
	private final String DAY = "day";
	private Date start;
	private Date end;

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String day = (String) session.getAttribute(DAY);

		setDayToToday();
		List<Worker> workers = ServiceWork.getWorkers(start, end);

		session.setAttribute("day_worker_list", workers);
		Timestamp ts = new Timestamp(start.getTime());
		long x = start.getTime();
		session.setAttribute("mytime", ts);
		System.out.println(ts);
		request.setAttribute("name", "Jack");
		request.getRequestDispatcher("/WEB-INF/jsp_admin/timeline.jsp")
				.forward(request, response);
		return null;
	}

	private void setDayToToday() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		start = c.getTime();
		System.out.println(start);
		c.add(Calendar.HOUR_OF_DAY, 24);
		end = c.getTime();
		System.out.println(end);
	}
}
