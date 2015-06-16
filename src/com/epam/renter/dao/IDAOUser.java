package com.epam.renter.dao;

import com.epam.renter.entities.User;

public interface IDAOUser {
	
	public User findByID(int idUser);

	public User findByLogin(String login);
	
	public User findByEmail(String email);

	public boolean create(User user);
}
