package com.epam.renter.entities;

import java.io.Serializable;

public class Work implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public Work() {
	}
	
	public Work(Application application, Worker worker) {
		this.application = application;
		this.worker = worker;
	}
	
	public Work(int id, Application application, Worker worker) {
		this.id = id;
		this.application = application;
		this.worker = worker;
	}

	private int id;
	private Application application;
	private Worker worker;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Application getApplication() {
		return application;
	}
	public void setApplication(Application application) {
		this.application = application;
	}
	public Worker getWorker() {
		return worker;
	}
	public void setWorker(Worker worker) {
		this.worker = worker;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((application == null) ? 0 : application.hashCode());
		result = prime * result + id;
		result = prime * result + ((worker == null) ? 0 : worker.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Work other = (Work) obj;
		if (application == null) {
			if (other.application != null)
				return false;
		} else if (!application.equals(other.application))
			return false;
		if (id != other.id)
			return false;
		if (worker == null) {
			if (other.worker != null)
				return false;
		} else if (!worker.equals(other.worker))
			return false;
		return true;
	}
	
		
}
