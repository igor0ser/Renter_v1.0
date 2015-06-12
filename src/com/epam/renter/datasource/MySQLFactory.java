package com.epam.renter.datasource;

import com.epam.renter.dao.*;

public class MySQLFactory {

	public final IDAOUser mySQLDAOUser = new MySQLDAOUser();
	
	public final IDAOAddress mySQLDAOAddress = new MySQLDAOAddress();
	
	public final IDAOAdmin MYSQLDAOAdmin = new MYSQLDAOAdmin();
	
	public final IDAOApplication mySQLDAOApplication  = new MySQLDAOApplication ();
	
	public final IDAOWorker mySQLDAOWorker= new MySQLDAOWorker();
	
	public final IDAOWork mySQLDAOWork= new MySQLDAOWork();
	
}
