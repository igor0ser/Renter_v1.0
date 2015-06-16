package com.epam.renter.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Worker implements Serializable {

	private static final long serialVersionUID = 1L;

	public Worker() {
		apps = new ArrayList<Application>();
	}
	
	public Worker(int id) {
		this.id = id;
		apps = new ArrayList<Application>();
	}

	private int id;
	private String name;
	private String surname;
	private TypeOfWork typeOfWork;
	private List<Application> apps;



	public List<Application> getApps() {
		return apps;
	}

	public void setApps(List<Application> apps) {
		this.apps = apps;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public TypeOfWork getTypeOfWork() {
		return typeOfWork;
	}
	public void setTypeOfWork(TypeOfWork typeOfWork) {
		this.typeOfWork = typeOfWork;
	}

	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Worker other = (Worker) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return name + " " + surname;
	}



}
