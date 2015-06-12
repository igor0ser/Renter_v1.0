package com.epam.renter.dao;

import com.epam.renter.entities.Admin;

public interface IDAOAdmin {

	public Admin findByUserID(int idUser);
	
}
