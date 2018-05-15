package com.controllerr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;



@Service
public class UserServi implements UserServ{

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private RoleRepo rolerepo;
	
	public List<User> users;
	
	
	public void save(User user){
		Role userRole = rolerepo.findByRole("ADMIN");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
       
        userRepo.save(user);
	    
	    users=new ArrayList<>();
        users.add(userRepo.save(user));
        System.out.println("users==>"+users);
	}

	public User findUserByEmail(String name) {
		return userRepo.findUserByEmail(name);
	}
	

	
	public User findById(Long id) {
	//	return users.stream().filter(t->t.getId().equals(id)).findFirst().get();
		return userRepo.findById(id);
	}
	

	public List<User> getAllEmployees() {
		 users =(List<User>) userRepo.findAll();
		System.out.println("users getall==>"+users);
		return users;
	 }
	
	//getting single user from the list
	public User retrieveUser(Long userId) {
		
		for (User student : users) {
			if (student.getId().equals(userId)) {
				return student;
			}
		  }
		return null;
	}
	
	
	/*public User findByConfirmationToken(String confirmationToken) {
		return userRepo.findByConfirmationToken(confirmationToken);
	}*/
	
	
	/*public void updateUser(Long id, User user) {
		System.out.println("dfsgfg==>"+users);
		for(int i=0;i<users.size();i++){
			User t=users.get(i);
		if(t.getId().equals(id)){
        users.set(i, user);			
		}
		}
		 userRepo.save(user);
	}*/

	@Override
	public User EditCustomer(Long id, User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateUser(Long id, User user) {
		
		
	}
	
}
