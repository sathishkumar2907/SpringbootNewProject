package com.controllerr;

import org.springframework.data.repository.CrudRepository;

public interface RoleRepo extends CrudRepository<Role, Integer>{
	Role findByRole(String role);
	
	
}
