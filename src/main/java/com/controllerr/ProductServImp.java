package com.controllerr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServImp implements ProductService{

	@Autowired
	public ProductRepo productRepo;
	
	public void saveProduct(ProductModel productModel) {
		productRepo.save(productModel);
	}

	
	
}
