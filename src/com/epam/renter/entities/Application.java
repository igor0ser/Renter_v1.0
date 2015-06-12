package com.epam.renter.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Application implements Serializable {

	private static final long serialVersionUID = 1L;

	public Application() {
		this.user = new User();

	}

	public Application(int id) {
		this.user = new User();
		this.id = id;
	}

	public Application(User user, String about, TypeOfWork typeOfWork,
			Date desirable) {
		this.user = user;
		this.about = about;
		this.status = Status.CREATED;
		this.typeOfWork = typeOfWork;
		this.creation = new Date();
		this.desirable = desirable;
	}

	public Application(int id, User user, String about, Status status,
			TypeOfWork typeOfWork, Date creation, Date desirable, Date start,
			Date end) {
		this.id = id;
		this.user = user;
		this.about = about;
		this.status = status;
		this.typeOfWork = typeOfWork;
		this.creation = creation;
		this.desirable = desirable;
		this.start = start;
		this.end = end;
	}

	private int id;
	private User user;
	private String about;
	private Status status;
	private TypeOfWork typeOfWork;
	private Date creation;
	private Date desirable;
	private Date start;
	private Date end;
	private List<Worker> workers;

	public List<Worker> getWorkers() {
		return workers;
	}

	public void setWorkers(List<Worker> workers) {
		this.workers = workers;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public TypeOfWork getTypeOfWork() {
		return typeOfWork;
	}

	public void setTypeOfWork(TypeOfWork typeOfWork) {
		this.typeOfWork = typeOfWork;
	}

	public Date getCreation() {
		return creation;
	}

	public void setCreation(Date creation) {
		this.creation = creation;
	}

	public Date getDesirable() {
		return desirable;
	}

	public void setDesirable(Date desirable) {
		this.desirable = desirable;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((about == null) ? 0 : about.hashCode());
		result = prime * result
				+ ((creation == null) ? 0 : creation.hashCode());
		result = prime * result
				+ ((desirable == null) ? 0 : desirable.hashCode());
		result = prime * result + ((end == null) ? 0 : end.hashCode());
		result = prime * result + id;
		result = prime * result + ((start == null) ? 0 : start.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result
				+ ((typeOfWork == null) ? 0 : typeOfWork.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Application other = (Application) obj;
		if (about == null) {
			if (other.about != null)
				return false;
		} else if (!about.equals(other.about))
			return false;
		if (creation == null) {
			if (other.creation != null)
				return false;
		} else if (!creation.equals(other.creation))
			return false;
		if (desirable == null) {
			if (other.desirable != null)
				return false;
		} else if (!desirable.equals(other.desirable))
			return false;
		if (end == null) {
			if (other.end != null)
				return false;
		} else if (!end.equals(other.end))
			return false;
		if (id != other.id)
			return false;
		if (start == null) {
			if (other.start != null)
				return false;
		} else if (!start.equals(other.start))
			return false;
		if (status != other.status)
			return false;
		if (typeOfWork != other.typeOfWork)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		String td = "</td><td>";
		StringBuilder sb = new StringBuilder();
		sb.append("<td>").append(user.getLogin()).append(td)
				.append(user.getPhoneNumber()).append(td)
				.append(user.getAddress()).append(td)
				.append(typeOfWork.toString()).append(td).append(about)
				.append(td).append(creation).append(td).append(desirable).append(td).append(start).append(td).append(end).append(td);
		if (workers != null) {
			for (Worker worker : workers) {
				sb.append(worker).append(", ");
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.deleteCharAt(sb.length() - 1);
		}
		sb.append("</td>");
		

		return sb.toString();
	}
}