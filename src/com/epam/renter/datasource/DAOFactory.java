package com.epam.renter.datasource;

import com.epam.renter.dao.IDAOAddress;
import com.epam.renter.dao.IDAOAdmin;
import com.epam.renter.dao.IDAOApplication;
import com.epam.renter.dao.IDAOUser;
import com.epam.renter.dao.IDAOWork;
import com.epam.renter.dao.IDAOWorker;
import com.epam.renter.dao.MYSQLDAOAdmin;
import com.epam.renter.dao.MySQLDAOAddress;
import com.epam.renter.dao.MySQLDAOApplication;
import com.epam.renter.dao.MySQLDAOUser;
import com.epam.renter.dao.MySQLDAOWork;
import com.epam.renter.dao.MySQLDAOWorker;

public class DAOFactory {

	public static IDAOUser getDAOUser() {
		return new MySQLDAOUser();
	}

	public static IDAOAddress getDAOAddress() {
		return new MySQLDAOAddress();
	}

	public static IDAOAdmin getDAOAdmin() {
		return new MYSQLDAOAdmin();
	}
	
	public static IDAOApplication getDAOApplication() {
		return new MySQLDAOApplication ();
	}

	public static IDAOWorker getDAOWorker() {
		return new MySQLDAOWorker();
	}
	
	public static IDAOWork getDAOWork() {
		return new MySQLDAOWork();
	}
}
