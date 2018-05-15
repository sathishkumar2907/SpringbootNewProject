package com.controllerr;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Category_servImp implements Category_servic{

	@Autowired
	private Category_Repo catRepos;
	
	@Autowired
	private Sub_Cat_Repo subcatRepos;
	
	public List<Category_Model> category_model;
	
	
	
	public void save_category(Category_Model category) {
		
		 catRepos.save(category);
		//catRepos.save(category);
		category_model=new ArrayList<>();
		category_model.add(catRepos.save(category));
		System.out.println("category_model====> "+category_model);
	 }


	
	public Category_Model findOne(Long id) {
		return catRepos.findOne(id);
	}

	/*public void addRole(Role role) {
    for(Resource resource: role.getResources()){        
        resource.setRole(role); 
    }
     roleRepository.save(role);
    }*/
	/*public Category_Model findCategory(String cat_name) {
		return catRepos.findCategory(cat_name);
	}*/
	
	public List<Category_Model> getAllCategories() {
		category_model =(List<Category_Model>) catRepos.findAll();
		System.out.println("category_model getall===>"+category_model);
		return category_model;
	 }
	
	
	
	

	

}















