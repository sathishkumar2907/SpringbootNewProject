package com.controllerr;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	
	//Api with Pathvariable
	    @PostMapping("/posts/{postId}/comments/")
	    @ResponseBody
	    public Response createComment(@PathVariable (value = "postId") Long postId, @Valid @RequestBody Sub_cat_Model comment) {
	    	Response respone=new Response();
	    	
	         Category_Model ss= (Category_Model) cat_ser_con_repo.findOne(postId);
	    	comment.setCat_id(ss);
	    	sub_cat_ser_con_repo.save(comment);
	    	respone.setSs("success");
	    	
	      /* return cat_ser_con_repo.findOne(postId).map(post -> {
	    	    comment.setCat_id(post);
	            return sub_cat_ser_con_repo.save(comment);
	       }).orElseThrow(() -> new ResourceNotFoundExcption("PostId " + postId + " not found"));*/
	    	return respone;
	    }
	
	    
	    //Api with RequestParam
	    @PostMapping("/posts/comments")
	    @ResponseBody
	    public Response createComment1(@RequestParam("cat_id") Long cat_id, Sub_cat_Model comment) {
	    	System.out.println("sdgsgdfghdf===============>");
	    	Response respone=new Response();
	    	System.out.println("postId===>"+cat_id);
	        Category_Model ss= (Category_Model) cat_ser_con_repo.findOne(cat_id);
	        System.out.println("comment===>"+comment);
	    	comment.setCat_id(ss);
	    	sub_cat_ser_con_repo.save(comment);
	    	respone.setSs("success");
	    	
	    	return respone;
	    }
	    
	    @RequestMapping(value="/savesub",method=RequestMethod.POST)  
	    @ResponseBody
	    public Response save_sub_category(@RequestBody /*@ModelAttribute*/ @Valid Sub_cat_Model sub_ategory_model,BindingResult result){//,@RequestParam("cat_id") Long cat_id,
		
		 Response respone=new Response();
	
		 /*Category_Model sub=cat_ser_con.findOne(cat_id);
		 System.out.println("sub=====>"+sub);*/
		// sub_ategory_model.setCat_name_model(sub);
		/* if (sub != null){
            result.rejectValue("cat_name",null,"There is already an Category with that name");
           }*/
		 // Sub_cat_Model currentUser= (Sub_cat_Model) sub_cat_ser_con_repo.findById(cat_id);
		  System.out.println("--------======>"+sub_ategory_model.getCat_id());
		  System.out.println("--------======>"+sub_ategory_model.getSub_cat_name());
		System.out.println("dfsdfhdkgfkdg");
		if(result.hasErrors()){
		     	//Get error message
	            Map<String, String> errors = result.getFieldErrors().stream()
	                  .collect(
	                        Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)
	                    );
	            
	            respone.setValidated(false);
	            respone.setErrorMessages(errors);
		
		 }else{
			System.out.println("sathish");
			   /* Category_Model ss= (Category_Model) cat_ser_con_repo.findOne(cat_id);
			    sub_ategory_model.setCat_id(ss);
			    System.out.println("ss===>"+ss);*/
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
		 //   Category_Model cai_id=new Category_Model();
		    Response respone=new Response();
		    Sub_cat_Model s=new Sub_cat_Model();
		    
		   // Category_Model ca_i=cat_ser_con_repo.findOne(2L);
		   /* Sub_cat_Model ss=sub_cat_ser_con_repo.findOne(s.getSub_cat_id());
		    respone.setRes_sub_cat(ss);*/
		    
	    
	    	List<Category_Model> user_list=(List<Category_Model>) cat_ser_con_repo.findAll();
	    	System.out.println("user_list-->"+user_list);
	    	
	    	 List<Sub_cat_Model> user_list1=(List<Sub_cat_Model>) sub_cat_ser_con_repo.findAll();
		     System.out.println("user_list_sub_cat-->"+user_list1);
		     
		    /* List list=new ArrayList();
	             for(Category_Model cc:user_list){
                     for(Sub_cat_Model cc123:user_list1){
		    		     if(cc.getCat_id()==cc123.getSub_cat_id()){
		    			   list.add(cc123);
		    			   list.add(cc);
		    		       System.out.println(cc123.getSub_cat_name()+" ===> "+cc123.getCat_id().getCat_name());
		    			   respone.setCat_sub_m(list);
		    		     }
		    	      }
	    	      }*/
	    	
	        respone.setValidated(true);
	    	//model.addAttribute("posts",user_list);
	    	respone.setCat_m(user_list);
	    	respone.setCat_sub_m(user_list1);
	    
			return respone;
		}
	
	   @RequestMapping(value="/getCatByID/{cat_id}", method=RequestMethod.POST)
	   @ResponseBody
		public Response getAllCategoriesById(@PathVariable("cat_id") long cat_id){
		Response respone=new Response();
		
		List<Sub_cat_Model> cat=(List<Sub_cat_Model>) sub_cat_ser_con_repo.findById(cat_id);
		System.out.println("cat==>"+cat);
		respone.setCat_sub_m(cat);
		
		return respone;
	}
	
	
    @RequestMapping(value="/categoriesInSub", method=RequestMethod.POST)
    @ResponseBody
	public Response getAllCategories123(){
	      
	    Response respone=new Response();
	    List<Category_Model> user_list=(List<Category_Model>)cat_ser_con_repo.myFindCustomerIds();
    	System.out.println("user_list-->"+user_list);
    	
        respone.setValidated(true);
    	respone.setCat_m(user_list);
    	
    
		return respone;
	}
	
}







