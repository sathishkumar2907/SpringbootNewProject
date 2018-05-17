package com.controllerr;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
//import com.controllerr.Sub_cat_Model;

public interface Sub_Cat_Repo extends CrudRepository<Sub_cat_Model, Long>{
	
	//public Sub_cat_Model findOne(Long id);

	/* @Query(value = "SELECT sub.* FROM sub_category as sub WHERE sub.cat_id=:cat_id", nativeQuery = true)
	    public Sub_cat_Model findById(List<Sub_cat_Model> list);
	*/
	 /* @Query(value = "SELECT sub.* FROM sub_category as sub WHERE sub.sub_cat_id=:sub_cat_id", nativeQuery = true)
	  public Sub_cat_Model findOne(Sub_cat_Model sub_cat_id);
	
	  @Query(value = "SELECT sub.* FROM sub_category as sub WHERE sub.cat_id=:cat_id", nativeQuery = true)
	  public List<Sub_cat_Model> findById(@Param("cat_id") Category_Model cat_id);*/
	
	@Query(value ="SELECT * FROM sub_category INNER JOIN category ON sub_category.cat_id=category.cat_id WHERE sub_category.cat_id=:cat_id", nativeQuery = true)
	//SELECT * FROM sub_category INNER JOIN category ON sub_category.cat_id=category.cat_id WHERE sub_category.cat_id=:cat_id
	//SELECT * FROM sub_category INNER JOIN category ON sub_category.cat_id=category.cat_id WHERE sub_category.cat_id=1 ORDER BY category.cat_id
	 public List<Sub_cat_Model> findById(@Param("cat_id") Long long1);
	 
	 

	 public Sub_cat_Model findOne(Long catid);
}
