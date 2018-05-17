package com.controllerr;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Response {
	   private User user;
	   private List<User> data;
	   
	

	private boolean validated;
	   private Map<String, String> errorMessages;
	   private List<Category_Model> cat_m;
	   private List<Sub_cat_Model> cat_sub_m;
	   
	   private Category_Model res_cat;
	   private Sub_cat_Model res_sub_cat;
	   
	   private List<UploadImageModel> upImagelist;
	   private UploadImageModel uplImg;
	  
	   private String ss=new String();
	   
	   public String getSs() {
		return ss;
	   }

	public void setSs(String ss) {
		this.ss = ss;
	}

	public String string1;
		public String getString1() {
		return string1;
	}

	public void setString1(String string1) {
		this.string1 = string1;
	}

		public Response(String string1) {
			this.string1=string1;
		}
	   
	   public List<UploadImageModel> getUpImagelist() {
		return upImagelist;
	}

	public void setUpImagelist(List<UploadImageModel> upImagelist) {
		this.upImagelist = upImagelist;
	}

	public UploadImageModel getUplImg() {
		return uplImg;
	}

	public void setUplImg(UploadImageModel uplImg) {
		this.uplImg = uplImg;
	}

	public Sub_cat_Model getRes_sub_cat() {
		return res_sub_cat;
	}

	public void setRes_sub_cat(Sub_cat_Model res_sub_cat) {
		this.res_sub_cat = res_sub_cat;
	}

	

	public Category_Model getRes_cat() {
		return res_cat;
	}

	public void setRes_cat(Category_Model res_cat) {
		this.res_cat = res_cat;
	}

	public List<Sub_cat_Model> getCat_sub_m() {
		return cat_sub_m;
	}

	public void setCat_sub_m(List<Sub_cat_Model> cat_sub_m) {
		this.cat_sub_m = cat_sub_m;
	}

	public List<Category_Model> getCat_m() {
		return cat_m;
	}

	public void setCat_m(List<Category_Model> cat_m) {
		this.cat_m = cat_m;
	}

	public User getUser() {
		return user;
	}
	
	public Response() {
		super();
	}
	
	

	public void setUser(User user) {
		this.user = user;
	}
	
	public boolean isValidated() {
		return validated;
	}
	
	public void setValidated(boolean validated) {
		this.validated = validated;
	}
	
	public Map<String, String> getErrorMessages() {
		return errorMessages;
	}
	
	public void setErrorMessages(Map<String, String> errorMessages) {
		this.errorMessages = errorMessages;
	}
	

	public List<User> getData() {
		return data;
	}

	public void setData(List<User> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Response [user=" + user + ", data=" + data + ", validated=" + validated + ", errorMessages="
				+ errorMessages + ", cat_m=" + cat_m + ", cat_sub_m=" + cat_sub_m + ", res_cat=" + res_cat
				+ ", res_sub_cat=" + res_sub_cat + ", upImagelist=" + upImagelist + ", uplImg=" + uplImg + "]";
	}




	
}

