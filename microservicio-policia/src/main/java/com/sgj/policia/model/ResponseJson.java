package com.sgj.policia.model;

import java.io.Serializable;

public class ResponseJson implements Serializable {

	private static final long serialVersionUID = 2903346984085038452L;

	private boolean success;
	
	private String message;

	
	public ResponseJson() {
		super();
	}

	public ResponseJson(boolean success, String message) {
		super();
		this.success = success;
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
