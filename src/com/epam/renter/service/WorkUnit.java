package com.epam.renter.service;

import java.sql.Timestamp;
import java.util.Date;

// type of data, what will be parsed by JavaScript
public class WorkUnit {
	private String name;
	private Timestamp start;
	private Timestamp end;

	public WorkUnit(String workerName, Date startDate, Date endDate) {
		this.name = workerName;
		this.start = new Timestamp(startDate.getTime());
		this.end = new Timestamp(endDate.getTime());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getStart() {
		return start;
	}

	public void setStart(Timestamp start) {
		this.start = start;
	}

	public Timestamp getEnd() {
		return end;
	}

	public void setEnd(Timestamp end) {
		this.end = end;
	}

	@Override
	public String toString() {
		return name + " " + start + "-" + end;
	}

}
