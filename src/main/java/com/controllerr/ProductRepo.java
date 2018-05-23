package com.controllerr;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ProductRepo extends CrudRepository<ProductModel, Long>{
	
	@Query(value = "SELECT * FROM product INNER JOIN sub_category ON product.sub_cat_id=sub_category.sub_cat_id WHERE product.sub_cat_id=:sub_cat_id",nativeQuery=true)
	 public List<ProductModel> findById(@Param("sub_cat_id") long sub_cat_id);
}
