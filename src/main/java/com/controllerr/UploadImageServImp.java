package com.controllerr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UploadImageServImp implements UploadImageServ{
	
	@Autowired
	private uploadImgaeRepo uploadImgaeRepo;
	
	
	public void save_uploadimage(UploadImageModel uploadImageModel){
		uploadImgaeRepo.save(uploadImageModel);
	}
	

}
