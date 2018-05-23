package com.controllerr;



import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;


@Controller
public class UserControl {
	
	public static final Logger logger = LoggerFactory.getLogger(UserControl.class);
	List<User> cust = new ArrayList<User>();
	
	@Autowired
	private UserServ userserv;
	
	@Autowired
	private UserRepo userrepo;
	
	/*@Autowired
	private EmailService emailService;*/
	
	
	List<User> list;
	 
	@RequestMapping("/")
	    public String index() {
		    return "index";
	    }
	
	@RequestMapping(value="/save")
    public String saveemp() {
	    return "save";
    }

	
	//saving the user with responseEntity for Api
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	@ResponseBody
    public Response createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
		Response respone=new Response();
		
		
		//while adding the use, it should added to list also ->It is the process to add to List
	/*	List<User> inventoryList = new ArrayList<User>();
    	inventoryList.add(user);
		System.out.println("inventoryList===>"+inventoryList);
		userrepo.save(inventoryList);*/
		
        System.out.println(user.getName());
        System.out.println(user.getEmail());
		user.setName(user.getName());
		user.setEmail(user.getEmail());
		
		
        userserv.save(user);
		
        
        respone.setString1("success");
        respone.setValidated(true);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/user/{id}").buildAndExpand(user.getId()).toUri());
        return respone;
    }
 
	
	//*******in html page, if you want to display error messages then @modelAttribute is used. For Apis @ModelAttribute 
	//annotation is not required then only it will dispaly the error messages in postman *****//Very Imp

	    @RequestMapping(value="/save1",method=RequestMethod.POST)  
	    @ResponseBody
	    public Response save(@RequestBody @ModelAttribute @Valid User user,BindingResult result, ModelMap model){  
	    	
	    	/*List<User> inventoryList = new ArrayList<User>();
	    	inventoryList.add(user);*/
	    	
	    	  Response respone=new Response();
	    	  
	          User user1 = userserv.findUserByEmail(user.getName());
	    	  System.out.println("user1====>"+user1);
	     	  
	    	  if (user1 != null){
	    		  result.rejectValue("name",null,"There is already an account registered with that name");
	          }
	    		
	    	  User user2=userserv.findUserByEmail(user.getEmail());
	    	  System.out.println("user1====>"+user2);
	    	 
	    	 if (user2 != null){
	              result.rejectValue("email",null,"There is already an account registered with that email");
	           }
	    	
	        if(result.hasErrors()){
	     
	        	//Get error message
	            Map<String, String> errors = result.getFieldErrors().stream()
	                  .collect(
	                        Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)
	                    );
	            
	            respone.setValidated(false);
	            respone.setErrorMessages(errors);
	          
	        }else{
	       
	           // ModelAndView modelAndView = new ModelAndView();
	        	user.setName(user.getName());
	        	user.setEmail(user.getEmail());
	            userserv.save(user);
	            
	       
	        	Map<String, String> map=new HashMap<>();
	            map.put("success", "User has been registered successfully");
	            
	            respone.setValidated(true);
	            respone.setErrorMessages(map);
	            respone.setUser(user);
	            
	            
	           // modelAndView.addObject("successMessage", " ");
	            System.out.println("userid==>"+user.getId().toString());
	          
	           }
	            return respone;
	         }
	    	
	     @RequestMapping(value="/save", method=RequestMethod.POST)
	     @ResponseBody
		 public Response getAllEmployees(ModelMap model){
	    
		    Response respone=new Response();
	  
		    
	    	List<User> user_list=userserv.getAllEmployees();
	    	System.out.println("user_list-->"+user_list);
	    	
	    	  //System.out.println(userserv.retrieveUser(58L));
	     	
	    	model.addAttribute("posts",user_list);
	        respone.setData(user_list);
	    	respone.setValidated(true);
	    
			return respone;
		}
	  
	  
	  @RequestMapping(value = "/save1/{id}", method = RequestMethod.PUT)
	  @ResponseBody
	  public Response editSaveUser(@PathVariable("id") long id,@RequestBody User user){
		  Response respone = new Response();
		
		  User currentUser= userserv.findById(id);
		  System.out.println("current user in edit==>"+currentUser);
		
		  if (currentUser == null) {
			    logger.error("Unable to update. User with id {} not found.", id);
	            respone.setString1("Unable to upate. User with id " + id + " not found without ResponseEntity");
	            System.out.println("inside if");
	           
	        }else{
	        	
		    System.out.println("out if");
		    currentUser.setName(user.getName());
	        currentUser.setEmail(user.getEmail());
	 
	        userserv.save(currentUser);
	        System.out.println("cAfter save==>"+currentUser);
		  respone.setValidated(true);
		  Map<String, String> map=new HashMap<>();
          map.put("success", "successfully");
		  respone.setErrorMessages(map);
		  System.out.println("id====>"+id);
	        }
		  return respone;
	  }
	  
	  
	  //updating the user with responseEntity
	  @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	    public ResponseEntity<?> updateUser(@PathVariable("id") long id, @RequestBody User user) {
	        
	 
	        User currentUser = userserv.findById(id);
	        System.out.println("current user in edit==>"+currentUser);
	        if (currentUser == null) {
	        	 System.out.println("inside if");
	            logger.error("Unable to update. User with id {} not found.", id);
	            return new ResponseEntity(new Response("Unable to upate. User with id " + id + " not found with ResponseEntity"), HttpStatus.NOT_FOUND);
	        }
	        System.out.println("outside if");
	        currentUser.setName(user.getName());
	        currentUser.setEmail(user.getEmail());
	 
	        userserv.save(currentUser);
	        return new ResponseEntity<User>(currentUser, HttpStatus.OK);
	    }
	  
	  
		 
	  @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
	  @ResponseBody
	  public Response deleteCustomer(@ModelAttribute @Valid User user,BindingResult result,@PathVariable("id") Long id) {
		  Response respone=new Response();
		  
		  User currentUser= userserv.findById(id);
		  System.out.println("current user in delete==>"+currentUser);
		  
		  if(currentUser==null){
	        System.out.println("in if");
	        logger.error("Unable to update. User with id {} not found.", id);
	        respone.setString1("Unable to delete. User with id " + id + " not found without ResponseEntity");
		  }else{
		  
		  userrepo.delete(id);
		  
		//  userrepo.deleteAll();
		  respone.setValidated(true);
		  Map<String, String> map=new HashMap<>();
          map.put("success", "successfully");
		  respone.setErrorMessages(map);
		  
		  System.out.println("id====>"+id);
		  }
		  return respone;
		}  
	  
	  
     } 
  