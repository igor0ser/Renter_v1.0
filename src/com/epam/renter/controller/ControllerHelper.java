package com.epam.renter.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.epam.renter.command.admin.CommandAddWorkers;
import com.epam.renter.command.admin.CommandAssignedApps;
import com.epam.renter.command.admin.CommandCreatedApps;
import com.epam.renter.command.admin.CommandFreeWorkers;
import com.epam.renter.command.admin.CommandHandleApp;
import com.epam.renter.command.admin.CommandSetAppComplete;
import com.epam.renter.command.user.CommandChangeLanguage;
import com.epam.renter.command.user.CommandCreateApp;
import com.epam.renter.command.user.CommandGoToCreateApp;
import com.epam.renter.command.user.CommandLogIn;
import com.epam.renter.command.user.CommandLogOut;
import com.epam.renter.command.user.CommandMyApplications;
import com.epam.renter.command.user.CommandRegistration;

public class ControllerHelper {

	private static final String COMMAND = "command";

	private static ControllerHelper instance = null;
	HashMap<String, ICommand> commands = new HashMap<String, ICommand>();

	private ControllerHelper() {
		commands.put("login", new CommandLogIn());
		commands.put("logout", new CommandLogOut());
		commands.put("registration", new CommandRegistration());
		commands.put("see_app_user", new CommandMyApplications());
		commands.put("go_to_create_app", new CommandGoToCreateApp());
		commands.put("create_app", new CommandCreateApp());
		commands.put("created_apps", new CommandCreatedApps());
		commands.put("handle_app", new CommandHandleApp());
		commands.put("free_workers", new CommandFreeWorkers());
		commands.put("add_workers", new CommandAddWorkers());
		commands.put("assigned_apps", new CommandAssignedApps());	
		commands.put("set_app_complete", new CommandSetAppComplete());
		commands.put("change_language", new CommandChangeLanguage());
	}

	public ICommand getCommand(HttpServletRequest request) {
		ICommand command = commands.get(request.getParameter(COMMAND));
		if (command == null) {

		}
		return command;
	}

	public static ControllerHelper getInstance() {
		if (instance == null) {
			instance = new ControllerHelper();
		}
		return instance;
	}
}
