package com.controllerr;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.NamedQuery;
import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Sub_Category_Controll {
	
	@Autowired
	private Sub_Cat_Servic sub_cat_ser_con;
	
	@Autowired
	private Category_servic cat_ser_con;
	
	
	@Autowired
	private Category_Repo cat_ser_con_repo;
	
	@Autowired
	private Sub_Cat_Repo sub_cat_ser_con_repo;
	
	
	
	@RequestMapping(value="/file")
    public String saveemp1() {
		
	    return "file";
    }

	   @RequestMapping(value="/savesub",method=RequestMethod.POST)  
	   @ResponseBody
	    public Response save_sub_category(@ModelAttribute @Valid Sub_cat_Model sub_ategory_model,BindingResult result, ModelMap model,@RequestParam("cat_id") Long cat_id){
		 
		 Response respone=new Response();
		
		 /*Category_Model sub=cat_ser_con.findOne(cat_id);
		 System.out.println("sub=====>"+sub);*/
		// sub_ategory_model.setCat_name_model(sub);
		/* if (sub != null){
            result.rejectValue("cat_name",null,"There is already an Category with that name");
           }*/
		 
		 System.out.println("--------======>"+sub_ategory_model.getCat_id());
		 
		 if(result.hasErrors()){
		     	//Get error message
	            Map<String, String> errors = result.getFieldErrors().stream()
	                  .collect(
	                        Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)
	                    );
	            
	            respone.setValidated(false);
	            respone.setErrorMessages(errors);
		 
		 }else{
			
			
			    sub_cat_ser_con.save_sub_category(sub_ategory_model);
			
		    	Map<String, String> map=new HashMap<>();
	            map.put("success", "successfully");
	            
	            respone.setValidated(true);
	            respone.setErrorMessages(map);
	            
	            
	            System.out.println("userid==>"+sub_ategory_model.getSub_cat_id().toString());
		    }
		 
		 return respone;  
	}
	
	// @ModelAttribute @Valid Sub_cat_Model sub_ategory_model
	
	@RequestMapping(value="/savesub1", method=RequestMethod.POST)
	   @ResponseBody
		public Response getAllCategories(ModelMap model){
		    Category_Model cai_id=new Category_Model();
		    Response respone=new Response();
		    Sub_cat_Model s=new Sub_cat_Model();
		    
		   // Category_Model ca_i=cat_ser_con_repo.findOne(2L);
		   /* Sub_cat_Model ss=sub_cat_ser_con_repo.findOne(s.getSub_cat_id());
		    respone.setRes_sub_cat(ss);*/
	    
	    	List<Category_Model> user_list=(List<Category_Model>) cat_ser_con_repo.findAll();
	    	System.out.println("user_list-->"+user_list);
	    	
	    
	    	
	    	 List<Sub_cat_Model> user_list1=(List<Sub_cat_Model>) sub_cat_ser_con_repo.findAll();
		     System.out.println("user_list_sub_cat-->"+user_list1);
	    
	    	respone.setValidated(true);
	    	//model.addAttribute("posts",user_list);
	    	respone.setCat_m(user_list);
	    	respone.setCat_sub_m(user_list1);
	    
			return respone;
		}
	
/*	   @RequestMapping(value="/savesub1/sub", method=RequestMethod.POST)
	   @ResponseBody
		public Response getAllCategorieswithsubID(Category_Model cat){
		Response respone=new Response();
		
		 Category_Model user_list12= (Category_Model) sub_cat_ser_con_repo.findById(cat);
	     System.out.println("suuuuuu-->"+user_list12);
		 
	     respone.setRes_cat(cat);
		
		return respone;
	}*/
}







