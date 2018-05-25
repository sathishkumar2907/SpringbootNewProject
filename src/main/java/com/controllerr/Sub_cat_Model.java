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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
/*@JsonIdentityInfo(
		generator=ObjectIdGenerators.IntSequenceGenerator.class, property="sub_category")*/
@Entity
@Table(name="sub_category")
public class Sub_cat_Model {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="sub_cat_id")
	private Long id;
	
	
	@NotEmpty(message="sub category should not be empty")
	@Column(name="sub_cat_name")
	private String sub_cat_name;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="cat_id")
	//@JoinTable(name="category",inverseJoinColumns={@JoinColumn(name ="cat_id")})
	private Category_Model cat_id;
		
	
	@JsonManagedReference
	/*@JsonIdentityInfo(
			generator=ObjectIdGenerators.IntSequenceGenerator.class, property="productModels")*/
	@OneToMany(cascade = CascadeType.PERSIST, mappedBy = "sub_cat_id",fetch = FetchType.EAGER)
	private List<ProductModel> productModels=new ArrayList<>();
	
	
	
	public Sub_cat_Model(){}
	
	public Long getId() {
		return id;
	}

	public List<ProductModel> getProductModels() {
		return productModels;
	}

	public void setProductModels(List<ProductModel> productModels) {
		this.productModels = productModels;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Category_Model getCat_id() {
		return cat_id;
	}

	public void setCat_id(Category_Model cat_id) {
		this.cat_id = cat_id;
	}
 
	public Long getSub_cat_id() {
		return id;
	}

	public void setSub_cat_id(Long sub_cat_id) {
		this.id = sub_cat_id;
	}

	public String getSub_cat_name() {
		return sub_cat_name;
	}

	public void setSub_cat_name(String sub_cat_name) {
		this.sub_cat_name = sub_cat_name;
	}

    @Override
	public String toString() {
		return "Sub_cat_Model [sub_cat_id=" + id + ", sub_cat_name=" + sub_cat_name + ", cat_id=" + cat_id
				+ "]";
	}

 }
