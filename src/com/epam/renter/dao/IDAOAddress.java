package com.epam.renter.dao;

import com.epam.renter.entities.Address;
import com.epam.renter.entities.User;

public interface IDAOAddress {
	
	public Address findByUser(User user);

	public boolean create(Address address);
	
}
