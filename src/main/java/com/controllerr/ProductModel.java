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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
/*@JsonIdentityInfo(
		generator=ObjectIdGenerators.IntSequenceGenerator.class, property="product")*/
@Entity
@Table(name = "product")
public class ProductModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="productid")
	private Long productid;
	
	@NotEmpty(message="Product Name should not be empty")
	@Column(name="productname")
	private String productname;
	
	@NotEmpty(message="Product Type should not be empty")
	@Column(name="producttype")
	private String producttype;
	
	
	/*@JsonIdentityInfo(
			generator=ObjectIdGenerators.IntSequenceGenerator.class, property="sub_cat_id")*///If u want to get product items list along with subcategory list use this
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="sub_cat_id")
	private Sub_cat_Model sub_cat_id;


	public ProductModel(){
	}
	
	
	
	public Sub_cat_Model getSub_cat_id() {
		return sub_cat_id;
	}

	public void setSub_cat_id(Sub_cat_Model sub_cat_id) {
		this.sub_cat_id = sub_cat_id;
	}

	public Long getProductid() {
		return productid;
	}


	public void setProductid(Long productid) {
		this.productid = productid;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public String getProducttype() {
		return producttype;
	}

	public void setProducttype(String producttype) {
		this.producttype = producttype;
	}
	

	@Override
	public String toString() {
		return "ProductModel [id=" + productid + ", productname=" + productname + ", productType=" + producttype
				+ ", sub_cat_id=" + sub_cat_id + "]";
	}
}
