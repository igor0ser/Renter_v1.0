package com.epam.renter.command.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.renter.controller.ICommand;
import com.epam.renter.properties.Config;

public class CommandLogOut implements ICommand {
	
	private final Logger logger = LogManager.getLogger(CommandLogOut.class.getName());

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//log out = destroy session
		request.getSession().invalidate();
		logger.info(String.format("User logged out"));
		request.getRequestDispatcher(Config.getInstance().getProperty(Config.INDEX)).forward(request,
				response);
		
		return null;
	}

}
