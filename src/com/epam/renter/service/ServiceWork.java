package com.epam.renter.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.epam.renter.datasource.DAOFactory;
import com.epam.renter.entities.Address;
import com.epam.renter.entities.Application;
import com.epam.renter.entities.TypeOfWork;
import com.epam.renter.entities.Work;
import com.epam.renter.entities.Worker;

//class is created to join Applications, Worker and Work tables
public class ServiceWork {
	// returns data to create Workplan
	public static List<WorkJS> getWorkplan(Date start, Date end, String title) {
		List<Worker> allWorkers = DAOFactory.getDAOWorker().readAll();
		List<Worker> busyWorkers = getBusyWorkersByTime(start, end);
		listMinusList(allWorkers, busyWorkers);
		allWorkers.addAll(busyWorkers);
		Collections.sort(allWorkers);
		List<WorkJS> workplan = new ArrayList<WorkJS>();
		// title of work plan
		workplan.add(new WorkJS(title, "", "", start, end));
		for (Worker worker : allWorkers) {
			String name = worker.toString() + " ("
					+ worker.getTypeOfWork().toString().toLowerCase() + ")";
			if (worker.getApps().size() == 0) {
				// if worker is free all day
				workplan.add(new WorkJS(name, "", "", end, end));
			} else {
				for (Application app : worker.getApps()) {
					Address address = DAOFactory.getDAOAddress().findByUser(
							app.getUser());
					workplan.add(new WorkJS(name, address.toString(), app
							.getAbout(), app.getStart(), app.getEnd()));
				}
			}
		}
		return workplan;
	}

	// list of all workers
	public static List<Worker> getFreeWorkers(Date start, Date end) {
		List<Worker> allWorkers = DAOFactory.getDAOWorker().readAll();
		List<Worker> busyWorkers = getBusyWorkersByTime(start, end);
		listMinusList(allWorkers, busyWorkers);
		return allWorkers;
	}

	// list of workers of one speciality
	public static List<Worker> getFreeWorkers(TypeOfWork typeOfWork,
			Date start, Date end) {
		List<Worker> allWorkers = DAOFactory.getDAOWorker().findByTypeOfWork(
				typeOfWork);
		List<Worker> busyWorkers = getBusyWorkersByTime(start, end);
		listMinusList(allWorkers, busyWorkers);
		return allWorkers;
	}

	// list of workers that work on one application
	public static List<Worker> getWorkersByApp(Application app) {
		List<Work> workList = DAOFactory.getDAOWork().findByApplication(app);
		List<Worker> workerList = new ArrayList<>();
		for (Work work : workList) {
			int idWorker = work.getWorker().getId();
			Worker worker = DAOFactory.getDAOWorker().findByID(idWorker);
			workerList.add(worker);
		}
		return workerList;
	}

	// return list of workers with filled list of applications
	private static List<Worker> getBusyWorkersByTime(Date start, Date end) {
		List<Worker> workerList = new ArrayList<Worker>();

		List<Application> appList = DAOFactory.getDAOApplication().findByTime(
				start, end);
		for (Application app : appList) {
			List<Work> workList = DAOFactory.getDAOWork()
					.findByApplication(app);
			for (Work work : workList) {
				Worker worker = work.getWorker();
				int index = workerList.indexOf(worker);
				if (index < 0) {
					worker = DAOFactory.getDAOWorker().findByID(worker.getId());
					worker.getApps().add(app);
					workerList.add(worker);
				} else {
					workerList.get(index).getApps().add(app);
				}
			}
		}

		return workerList;
	}

	private static void listMinusList(List<Worker> list1, List<Worker> list2) {
		for (Worker worker : list2) {
			list1.remove(worker);
		}
	}
}
