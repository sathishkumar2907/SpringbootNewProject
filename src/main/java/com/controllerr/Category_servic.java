package com.controllerr;

import java.util.List;

public interface Category_servic {
	
	public void save_category(Category_Model category); 
	//public Category_Model findCategory(String cat_name);
	
	
	
	public List<Category_Model> getAllCategories();
	public Category_Model findOne(Long id);
	
}
