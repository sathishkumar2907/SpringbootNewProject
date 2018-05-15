package com.controllerr;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SearchController {
	
	@Autowired
	private UserServ userserv;
	
	
	 @RequestMapping(value="/api/search",method=RequestMethod.POST)
	 @ResponseBody
	    public ResponseEntity<?> search(
			@Valid @RequestBody SearchCriteria search, Errors errors) {

	        ImageUploadResponse result = new ImageUploadResponse();

	        //If error, just return a 400 bad request, along with the error message
	       if (errors.hasErrors()) {

				// get all errors 
	            result.setMsg(errors.getAllErrors()
					.stream()
					.map(x -> x.getDefaultMessage())
					.collect(Collectors.joining(",")));
					
	            return ResponseEntity.badRequest().body(result);

	        }
	        
	       

	        List<User> users = (List<User>) userserv.getAllEmployees();
           System.out.println("====>"+search.getUsername());
	        if(search.getUsername().equals("")){
	        	System.out.println("emptu");
	        	result.setMsg("empty");
	        }
	        
	        if (users.isEmpty()) {
	            result.setMsg("no user found!");
	        } else {
	            result.setMsg("success");
	        }
	        result.setResult(users);

	        return ResponseEntity.ok(result);

	    }
	 
	 
	 

     }
