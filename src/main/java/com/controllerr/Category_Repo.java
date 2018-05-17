package com.controllerr;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface Category_Repo extends CrudRepository<Category_Model, Long>{
  
//	public Category_Model findCategory(String Cat_name);
	//Category_Model findBycategory(Iterable<Category_Model> iterable);
	public Category_Model findOne(Long id);

//	public Response subcatModels(String name);
	
	/*@Query(value = "SELECT * FROM sub_category INNER JOIN category ON sub_category.cat_id=category.cat_id WHERE sub_category.cat_id=:cat_id", nativeQuery = true)
	 public Category_Model findById(@Param("cat_id") Long cat_id);*/
	
	@Query(value = "select * from category",nativeQuery = true)
	List<Category_Model> myFindCustomerIds();


}
