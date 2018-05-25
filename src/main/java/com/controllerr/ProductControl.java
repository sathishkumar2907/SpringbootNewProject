package com.controllerr;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProductControl {
	public static final Logger logger = LoggerFactory.getLogger(UserControl.class);
	
	@Autowired
	public ProductService pService; 
	
	@Autowired
	public Sub_Cat_Repo subCatRepo; 
	
	@Autowired
	public ProductRepo productRepo; 
	
	@Autowired
	public Category_Repo categoryRepo; 
	
	
	
	    @RequestMapping(value="/product/{subCatId}",method=RequestMethod.POST)
	    @ResponseBody
	    public Response createProduct(@PathVariable(value="subCatId") long subCatId,@RequestBody ProductModel productModel) {
	    	Response respone=new Response();
	    	
	    	
	        Sub_cat_Model productnameId= (Sub_cat_Model) subCatRepo.findOne(subCatId);
	        productModel.setSub_cat_id(productnameId);
	        System.out.println("2 ->"+productnameId);
	        
	       /* Category_Model productnamCateId= (Category_Model) categoryRepo.findOne(subCatId);
	        productModel.setCat_id(productnamCateId);
	        System.out.println("1 ->"+productnamCateId);*/
	        
	        
	        if (productnameId == null) {
			    logger.error("Unable to update. User with id {} not found.", subCatId);
	            respone.setString1("Unable to upate. User with id " + subCatId + " not found without ResponseEntity");
	            System.out.println("inside if");
	           
	        }else{
	       
	        	pService.saveProduct(productModel);
	            System.out.println("productModel=====>"+productModel);
	    	    respone.setSs("success");
	        }
	    	return respone;
	    }
	    
	    @RequestMapping(value="/categoriesInSub/product", method=RequestMethod.POST)
	    @ResponseBody
		public Response getAllCategories123(){
		    
		    Response respone=new Response();
		    List<Sub_cat_Model> user_list=(List<Sub_cat_Model>)subCatRepo.findAll();
	    	System.out.println("user_list-->"+user_list);
	    	
	        respone.setValidated(true);
	    	respone.setCat_sub_m(user_list);
	    	
			return respone;
		}
	
	       @RequestMapping(value="/getProductByID/{sub_cat_id}", method=RequestMethod.POST)
		   @ResponseBody
			public Response getAllCategoriesById(@PathVariable("sub_cat_id") long sub_cat_id){
			Response respone=new Response();
			
			List<ProductModel> subCat=(List<ProductModel>) productRepo.findById(sub_cat_id);
			System.out.println("cat==>"+subCat);
			respone.setProductRes(subCat);
			
			return respone;
		}
	       
	       
	       @RequestMapping(value="/getAllProducts", method=RequestMethod.POST)
		   @ResponseBody
			public Response getAllProducts(){
			Response respone=new Response();
			
		  /*Sub_cat_Model subCatModel=new Sub_cat_Model();
			subCatModel.setId(3L);
			subCatModel.setSub_cat_name("sgsd");
			
			ProductModel productModel=new ProductModel();
			productModel.setProductid(18L);
			productModel.setProductname("asdfasf");
			productModel.setProducttype("adfdf");
			subCatModel.setProductModels(productModel);
			
			List<ProductModel> rolelist = new ArrayList<>();
			
			List<Sub_cat_Model> userlist = new ArrayList<>();
			userlist.add(subCatModel);
			//productModel.setUserlist(userlist);
			rolelist.add(productModel);
			
			for (ProductModel r: rolelist) {
				for (Sub_cat_Model u: r.getUserlist())
					u.setProductModels(null);
			}*/
			
			List<ProductModel> subCat=(List<ProductModel>) productRepo.findAll();
			System.out.println("cat==>"+subCat);
			respone.setProductRes(subCat);
			
			return respone;
		}
	 }
