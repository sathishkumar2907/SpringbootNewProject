package com.controllerr;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="files")
public class UploadImageModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@NotEmpty(message="file should not be Empty...")
	@Column(name="file_name")
	private String file_name;
	
	//@JsonIgnore
	@NotEmpty(message="username should not be Empty")
	@Column(name="username")
	private String username;
	

	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public UploadImageModel(){}
	
	public UploadImageModel(Long id, String file_name) {
		super();
		this.id = id;
		this.file_name = file_name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	@Override
	public String toString() {
		return "UploadImageModel [id=" + id + ", file_name=" + file_name + ", username=" + file_name + "]";
	}

 }
