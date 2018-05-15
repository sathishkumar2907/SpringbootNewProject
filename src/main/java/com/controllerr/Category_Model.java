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
	
	@OneToMany(cascade =  CascadeType.ALL, mappedBy = "cat_id",fetch = FetchType.LAZY)
	private List<Sub_cat_Model> subcatModels=new ArrayList<>();
	
	
	
	/*@OneToMany(cascade = CascadeType.ALL, mappedBy = "category_model")
	//@JoinTable(name = "sub_category", joinColumns = @JoinColumn(name = "cat_id"), inverseJoinColumns = @JoinColumn(name = "sub_cat_id"))
	private Set<Category_Model> Sub_cat_Model;*/
	
	/*@OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
	private List<Category_Model> resources;*/
	
	
	
	/*public List<Category_Model> getResources() {
		return resources;
	}

	public void setResources(List<Category_Model> resources) {
		this.resources = resources;
	}*/
	
	public List<Sub_cat_Model> getSubcatModels() {
		return subcatModels;
	}

	public void setSubcatModels(List<Sub_cat_Model> subcatModels) {
		this.subcatModels = subcatModels;
	}

	public Category_Model(){}
	
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


	/*public List<Sub_cat_Model> getSub_cat_Models() {
		return subcatModels;
	}

	public void setSub_cat_Models(List<Sub_cat_Model> sub_cat_Models) {
		this.subcatModels = sub_cat_Models;
	}*/

	@Override
	public String toString() {
		return "Category_Model [cat_id=" + cat_id + ", cat_name=" + cat_name +  "]";
	}
	
	
}
