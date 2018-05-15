package com.controllerr;

import java.util.List;

public interface UserServ {

	public void save(User user);

	public User findUserByEmail(String name);
	
	public List<User> getAllEmployees();
	public User findById(Long id);

	public User EditCustomer(Long id, User user);
	public void updateUser(Long id, User user);
	
	public User retrieveUser(Long userId);
	
}
