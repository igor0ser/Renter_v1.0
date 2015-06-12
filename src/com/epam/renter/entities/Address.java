package com.epam.renter.entities;

import java.io.Serializable;

public class Address implements Serializable {

	private static final long serialVersionUID = 1L;

	public Address() {
	}

	public Address(String street, String house, String appartment, User user) {
		this.street = street;
		this.house = house;
		this.appartment = appartment;
		this.user = user;
	}

	public Address(int id, String street, String house, String appartment,
			User user) {
		this.id = id;
		this.street = street;
		this.house = house;
		this.appartment = appartment;
		this.user = user;
	}

	private int id;
	private String street;
	private String house;
	private String appartment;
	private User user;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHouse() {
		return house;
	}

	public void setHouse(String house) {
		this.house = house;
	}

	public String getAppartment() {
		return appartment;
	}

	public void setAppartment(String appartment) {
		this.appartment = appartment;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((appartment == null) ? 0 : appartment.hashCode());
		result = prime * result + ((house == null) ? 0 : house.hashCode());
		result = prime * result + id;
		result = prime * result + ((street == null) ? 0 : street.hashCode());
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
		Address other = (Address) obj;
		if (appartment == null) {
			if (other.appartment != null)
				return false;
		} else if (!appartment.equals(other.appartment))
			return false;
		if (house == null) {
			if (other.house != null)
				return false;
		} else if (!house.equals(other.house))
			return false;
		if (id != other.id)
			return false;
		if (street == null) {
			if (other.street != null)
				return false;
		} else if (!street.equals(other.street))
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
		return street + ", " + house + ", " + appartment;
	}

}
