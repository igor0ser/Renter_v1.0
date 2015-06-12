package com.epam.renter.dao;

import java.util.List;

import com.epam.renter.entities.TypeOfWork;
import com.epam.renter.entities.Worker;

public interface IDAOWorker {

	public List<Worker> findByTypeOfWork(TypeOfWork typeOfWork);
	
	public List<Worker> readAll();
	
	public Worker findByID(int id);
	
}
