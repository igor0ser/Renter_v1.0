package com.epam.renter.properties;

import java.util.ResourceBundle;

public class Config {

	private static Config instance;
	private ResourceBundle resource;
	private static final String BUNDLE_NAME = "com.epam.renter.properties.config";
	public static final String INDEX = "INDEX";
	public static final String WELCOME = "WELCOME";
    public static final String ERROR_LOGIN = "ERROR_LOGIN";
    public static final String MY_APPS = "MY_APPS";
    public static final String THANK_YOU = "THANK_YOU";
    public static final String ERROR_DB = "ERROR_DB";
    public static final String ADMIN_MESSAGE = "ADMIN_MESSAGE";
    public static final String ADMIN_CREATED_APPS = "ADMIN_CREATED_APPS";
    public static final String ADMIN_ASSIGNED_APPS = "ADMIN_ASSIGNED_APPS";
    public static final String ADMIN_HANDLE_APP = "ADMIN_HANDLE_APP";
    public static final String ADMIN_FREE_WORKERS = "ADMIN_FREE_WORKERS";
    public static final String GO_TO_CREATE_APP = "GO_TO_CREATE_APP";

    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
            instance.resource = ResourceBundle.getBundle(BUNDLE_NAME);
        }
        return instance;
    }
	
	   private Config() {
	    }
	   public String getProperty(String key) {
	        return (String) resource.getObject(key);
	    }

}
