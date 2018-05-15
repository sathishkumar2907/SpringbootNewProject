package com.controllerr;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface uploadImgaeRepo extends CrudRepository<UploadImageModel, Long>{

	public UploadImageModel findOne(Long id);

}
