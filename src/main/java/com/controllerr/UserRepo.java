package com.controllerr;

import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {
	//User findByConfirmationToken(String confirmationToken);
	public User findUserByEmail(String email);
	public User findById(Long id);

}
