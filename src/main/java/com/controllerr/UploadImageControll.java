package com.controllerr;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.thymeleaf.util.StringUtils;

@Controller
public class UploadImageControll {

	@Autowired
	public UploadImageServ uploadimageserv;
	
	@Autowired
	public uploadImgaeRepo uploadImgaeRepo;
	
	@RequestMapping(value="/upload")
	public String upload(){
		return "upload";
	}
	
	//headers=("content-type=multipart/*"),consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},@RequestParam("id") Long id,
	@RequestMapping(value="/upload",headers=("content-type=multipart/*"), method=RequestMethod.POST)
	@ResponseBody
	public Response upload(@ModelAttribute UploadImageModel fileInfo,BindingResult bindingResult,@RequestParam("file") MultipartFile inputFile, HttpServletResponse response1, HttpServletRequest request1){
		//@ModelAttribute UploadImageModel fileInfo,
		  //UploadImageModel fileInfo = new UploadImageModel();
		  HttpHeaders headers = new HttpHeaders();
		  Response result=new Response();
		  
		 
		  if(bindingResult.hasErrors()){
			  	//Get error message
	            Map<String, String> errors = bindingResult.getFieldErrors().stream()
	                  .collect(
	                        Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)
	                    );
	            
	            result.setValidated(false);
	            result.setErrorMessages(errors);
		 
		 }
		  
		  result.setValidated(true);
		  Map<String, String> map=new HashMap<>();
          map.put("success", "successfully");
		  result.setErrorMessages(map);
		  
		  
		  System.out.println(inputFile.getContentType());
		  if(inputFile.getContentType().equalsIgnoreCase("image/png")){
			  Map<String, String> map4=new HashMap<>();
	          map4.put("png", "successfully");
			  result.setErrorMessages(map4);
		  }
		  
		 /* if(res.hasErrors()){
			    //Get error message
	            Map<String, String> errors = res.getFieldErrors().stream()
	                  .collect(
	                        Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)
	                    );
	                 result.setErrorMessages(errors);
		           	}*/  
	    try {
	    	
	    	long size = inputFile.getSize();
	    	System.out.println("size===>"+size);
	    	
	    	if(inputFile.isEmpty()){
	    		Map<String, String> map3=new HashMap<>();
	            map3.put("message1", "file shouold not be empty");
	  		    result.setErrorMessages(map3);
	    		//String errMsg = "{\"message\":\"upload a file\"}";
	    		//return new ResponseEntity(errMsg, HttpStatus.BAD_REQUEST);
	    	}
	    	
	    	if(size>100000){
	    		
	    		Map<String, String> map1=new HashMap<>();
	            map1.put("message", "please upload a small file");
	    		result.setErrorMessages(map1);
	    		//String errMsg = "{\"message\":\"file size exceeded\"}";
	    		//return new ResponseEntity(errMsg, HttpStatus.BAD_REQUEST);
	    	}
	    	
	       //String path="D:\\Springworkspace\\NewProject\\Image\\";
        	
	    	String path = "C:/xampp/htdocs/NewProject/Image";
		    String originalFilename = inputFile.getOriginalFilename();
		        
		    DateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
		    String filename = df.format(new Date())+ "." + "jpg";
		    System.out.println("filename=====>"+filename);
	    	
	        File destinationFile = new File(path, filename);
        	System.out.println("path=====> "+destinationFile);
        	
        	
	        inputFile.transferTo(destinationFile);
	       
	       //fileInfo.setFileSize(inputFile.getSize());
	         fileInfo.setFile_name("/Image/"+destinationFile.getName());
	         System.out.println("username====>"+fileInfo.getUsername());
	        
	        uploadimageserv.save_uploadimage(fileInfo);
	        headers.add("File Uploaded Successfully - ", originalFilename);
   
	        result.setUplImg(fileInfo);
	        
            //String errMsg = "{\"message\":\"image upload successfully\"}";
	        // return new ResponseEntity(errMsg, headers, HttpStatus.OK);
      
	    } catch (Exception e) {
	        e.printStackTrace();
	     //   return new ResponseEntity("Upload fialed", HttpStatus.BAD_REQUEST);
	    }
		return result;   
	}
	
	
	@RequestMapping(value="/getUploadedImage", method=RequestMethod.POST)
	@ResponseBody
	public Response getImages(@ModelAttribute UploadImageModel file) throws IOException{
		Response response=new Response();
		
		List<UploadImageModel> list_images=(List<UploadImageModel>) uploadImgaeRepo.findAll();
		System.out.println("list_images==>"+list_images.size());
		
		response.setValidated(true);
		response.setUpImagelist(list_images);
		
		//========
	     /*for(UploadImageModel upImg : list_images){
        	response.setUplImg(upImg);
		}*/
		//========
        
		return response;
	}
	
	//Api with ResponseEntity
	@RequestMapping(value = "/upload/{id}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
	public ResponseEntity<UploadImageModel> getImage(@PathVariable("id")Long id, HttpServletResponse response){
	    UploadImageModel image =  uploadImgaeRepo.findOne(id);  //this just gets the data from a database
	    response.setContentType(MediaType.IMAGE_JPEG_VALUE);
	    Response response1=new Response();
	    response1.setValidated(true);
	    response1.setUplImg(image);
	   // response1.setUpImagelist(image);
	    //return response1;
	    return ResponseEntity.ok(image);
	}
	
	
 }
