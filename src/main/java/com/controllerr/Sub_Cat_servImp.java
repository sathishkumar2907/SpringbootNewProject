package com.controllerr;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class Sub_Cat_servImp implements Sub_Cat_Servic{

	@Autowired
	public Category_Repo category_Repo;
	
	@Autowired
	public Sub_Cat_Repo sub_Cat_Repo;
	
	/*public void addRole(Role role) {
    for(Resource resource: role.getResources()){        
	        resource.setRole(role); 
	    }
       roleRepository.save(role);
	}*/
	
	public void save_sub_category(Sub_cat_Model sub_cat_Model) {
		sub_Cat_Repo.save(sub_cat_Model);
	}

	
	/*public Sub_cat_Model findOne(Sub_cat_Model sub_cat_Model) {
		return sub_Cat_Repo.findOne(sub_cat_Model);
	}*/

	/*public Sub_cat_Model findById(Category_Model cat_id) {
		return (Sub_cat_Model) sub_Cat_Repo.findById(cat_id);
	}*/



}
