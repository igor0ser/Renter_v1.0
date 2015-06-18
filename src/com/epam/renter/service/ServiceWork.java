package com.epam.renter.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.epam.renter.datasource.DAOFactory;
import com.epam.renter.entities.Application;
import com.epam.renter.entities.TypeOfWork;
import com.epam.renter.entities.Work;
import com.epam.renter.entities.Worker;
//class is created to join Applications, Worker and Work tables
public class ServiceWork {
	//returns data to create Workplan
	public static List<WorkUnit> getWorkplan(Date start, Date end){
		List<Worker> allWorkers = DAOFactory.mySQLFactory.mySQLDAOWorker
				.readAll();
		List<Worker> busyWorkers = getBusyWorkersByTime(start, end);
		listMinusList(allWorkers, busyWorkers);
		allWorkers.addAll(busyWorkers);
		Collections.sort(allWorkers);
		List<WorkUnit> workplan = new ArrayList<WorkUnit>();
		String title = start.toString() + " - " + end.toString();
		workplan.add(new WorkUnit(title, start, end));
		for (Worker worker : allWorkers) {
			String name = worker.toString();
			if (worker.getApps().size()==0){
				workplan.add(new WorkUnit(name, end, end));
			} else{
				for (Application app : worker.getApps()){
					workplan.add(new WorkUnit(name, app.getStart(), app.getEnd()));
				}
			}
		}
		System.out.println(Arrays.toString(workplan.toArray()));
		return workplan;
	}
	
	
// list of all workers
	public static List<Worker> getFreeWorkers(Date start, Date end) {
		List<Worker> allWorkers = DAOFactory.mySQLFactory.mySQLDAOWorker
				.readAll();
		List<Worker> busyWorkers = getBusyWorkersByTime(start, end);
		listMinusList(allWorkers, busyWorkers);
		return allWorkers;
	}
	
//list of workers of one speciality
	public static List<Worker> getFreeWorkers(TypeOfWork typeOfWork,
			Date start, Date end) {
		List<Worker> allWorkers = DAOFactory.mySQLFactory.mySQLDAOWorker
				.findByTypeOfWork(typeOfWork);
		List<Worker> busyWorkers = getBusyWorkersByTime(start, end);
		listMinusList(allWorkers, busyWorkers);
		return allWorkers;
	}

	//list of workers that work on one application
	public static List<Worker> getWorkersByApp(Application app) {
		List<Work> workList = DAOFactory.mySQLFactory.mySQLDAOWork
				.findByApplication(app);
		List<Worker> workerList = new ArrayList<>();
		for (Work work : workList) {
			int idWorker = work.getWorker().getId();
			Worker worker = DAOFactory.mySQLFactory.mySQLDAOWorker
					.findByID(idWorker);
			workerList.add(worker);
		}
		return workerList;
	}

// return list of workers with filled list of applications	
	private static List<Worker> getBusyWorkersByTime(Date start, Date end){
		List<Worker> workerList = new ArrayList<Worker>();
		
		List<Application> appList = DAOFactory.mySQLFactory.mySQLDAOApplication.findByTime(start, end);
		for (Application app : appList){
			List<Work> workList = DAOFactory.mySQLFactory.mySQLDAOWork.findByApplication(app);
			for (Work work : workList){
				Worker worker = work.getWorker();
				int index = workerList.indexOf(worker);
				if (index<0){
					worker = DAOFactory.mySQLFactory.mySQLDAOWorker.findByID(worker.getId());
					worker.getApps().add(app);
					workerList.add(worker);
					}
				else{
					workerList.get(index).getApps().add(app);
				}
			}				
		}
		
		return workerList;
	}
	
	private static void listMinusList(List<Worker> list1, List<Worker> list2){
		for (Worker worker : list2){
			list1.remove(worker);
		}
	}
}
