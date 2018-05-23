package com.controllerr;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

@Entity
@Table(name = "category")

public class Category_Model {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="cat_id")
	private Long cat_id;
	
	@NotEmpty(message="Category should not be Empty")
	@Column(name="cat_name")
	private String cat_name;
	
	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cat_id",fetch = FetchType.EAGER)
	private List<Sub_cat_Model> subcatModels=new ArrayList<>();
	
	public Category_Model(){}
	

	public List<Sub_cat_Model> getSubcatModels() {
		return subcatModels;
	}

	public void setSubcatModels(List<Sub_cat_Model> subcatModels) {
		this.subcatModels = subcatModels;
	}

	
	
	public Category_Model(Long cat_id,String cat_name){
		this.cat_id=cat_id;
		this.cat_name=cat_name;
	}

	

	public Long getCat_id() {
		return cat_id;
	}

	public void setCat_id(Long cat_id) {
		this.cat_id = cat_id;
	}

	

	public String getCat_name() {
		return cat_name;
	}

	public void setCat_name(String cat_name) {
		this.cat_name = cat_name;
	}



	@Override
	public String toString() {
		return "Category_Model [cat_id=" + cat_id + ", cat_name=" + cat_name +  "]";
	}
	
	
}
