package com.controllerr;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface Category_Repo extends CrudRepository<Category_Model, Long>{
  
//	public Category_Model findCategory(String Cat_name);
	//Category_Model findBycategory(Iterable<Category_Model> iterable);
	public Category_Model findOne(Long id);

//	public Response subcatModels(String name);


}
