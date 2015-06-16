package com.epam.renter.command.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.renter.controller.ICommand;
import com.epam.renter.properties.Config;

//command that change language of site
public class CommandChangeLanguage implements ICommand {
	private final static String LANG = "lang";
	private final static String LANGUAGE = "language";
	private final static String LAST_PAGE = "last_page";
	private final Logger logger = LogManager
			.getLogger(CommandChangeLanguage.class.getName());

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String lang = request.getParameter(LANG);
			
		session.setAttribute(LANGUAGE, lang);

		// command returns to last url
		String last_url = (String) session.getAttribute(LAST_PAGE);
		if (last_url == null) {
			request.getRequestDispatcher(
					Config.getInstance().getProperty(Config.INDEX)).forward(
					request, response);
			logger.info(String.format(
					"Language is changed to %s. Command redirects to %s", lang,
					Config.getInstance().getProperty(Config.INDEX)));
		} else {
			request.getRequestDispatcher(last_url).forward(request, response);
			logger.info(String.format(
					"Language is changed to %s. Command redirects to %s", lang,
					last_url));
		}

		return null;
	}

}
