package com.epam.renter.command.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.renter.controller.ICommand;
import com.epam.renter.properties.Config;

public class CommandGoToCreateApp implements ICommand {

	private final static String LAST_PAGE = "last_page";
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// just redirects to create_application.jsp
		request.getSession().setAttribute(LAST_PAGE,
				Config.getInstance().getProperty(Config.GO_TO_CREATE_APP));
		request.getRequestDispatcher(
				Config.getInstance().getProperty(Config.GO_TO_CREATE_APP))
				.forward(request, response);

		return null;
	}

}
