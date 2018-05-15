package com.controllerr;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Category_Control {

	@Autowired
	private Category_servic catservice;
	
	@Autowired
	private Category_Repo catrep;
	
	
	@RequestMapping(value="/saveemp")
    public String saveemp1() {
	    return "saveemp";
    }
	
	 @RequestMapping(value="/saveemp",method=RequestMethod.POST)  
	   @ResponseBody
	    public Response save_category(@ModelAttribute @Valid Category_Model category_model,BindingResult result, ModelMap model){
		 
		 Response respone=new Response();
		 
		
		/* Category_Model cat_model = catservice.findCategory(category_model.getCat_name());
   	     System.out.println("user1====>"+cat_model);
		 
    	 if (cat_model != null){
              result.rejectValue("cat_name",null,"There is already an Category with that name");
          }*/
		
		 
		 if(result.hasErrors()){
		     
	        	//Get error message
	            Map<String, String> errors = result.getFieldErrors().stream()
	                  .collect(
	                        Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)
	                    );
	            
	            respone.setValidated(false);
	            respone.setErrorMessages(errors);
		 
		 }else{
			
			    catservice.save_category(category_model);
			   System.out.println("category id's========>"+category_model.getCat_id()+"  <======category data wth id=====>"+category_model);
		    	Map<String, String> map=new HashMap<>();
	            map.put("success", "successfully");
	            
	            respone.setValidated(true);
	            respone.setErrorMessages(map);
	            //respone.setCat_m(category_model);
	            //respone.setUser(category_model);
	            //System.out.println("userid==>"+category_model.getCat_id().toString());
		 }
		 
		 return respone;  
	}
	 
	 	  
	 

}
