package com.controllerr;

import java.util.List;

public class ImageUploadResponse {

	private String errormessage;
	private String Msg;
	private List<User> Result;
	
	
	public List<User> getResult() {
		return Result;
	}

	public void setResult(List<User> users) {
		Result = users;
	}

	public String getMsg() {
		return Msg;
	}

	public void setMsg(String msg) {
		this.Msg = msg;
	}

	public String getErrormessage() {
		return errormessage;
	}

	public void setErrormessage(String errormessage) {
		this.errormessage = errormessage;
	}
	
	@Override
	public String toString() {
		return "ImageUploadResponse [errormessage=" + errormessage + ", Msg=" + Msg + ", Result=" + Result + "]";
	}
	
}
