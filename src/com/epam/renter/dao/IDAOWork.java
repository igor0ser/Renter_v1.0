package com.epam.renter.dao;

import java.util.List;

import com.epam.renter.entities.Application;
import com.epam.renter.entities.Work;
import com.epam.renter.entities.Worker;

public interface IDAOWork {

	public List<Work> findByWorker(Worker worker);
	
	public List<Work> findByApplication(Application application);
	
	public boolean create(Work work);
}
