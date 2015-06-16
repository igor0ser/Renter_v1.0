package com.epam.renter.dao;

import java.util.Date;
import java.util.List;

import com.epam.renter.entities.Application;
import com.epam.renter.entities.Status;

public interface IDAOApplication {

	public Application findByID(int ID);
	
	public List<Application> findByUserID(int userID);
	
	public List<Application> findByStatus(Status status);
	
	public List<Application> findByTime(Date start, Date end);
	
	public boolean create(Application application);
	
	public boolean update(Application application);
	
}
