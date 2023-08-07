package com.csir.vehiclerequisition.request;

public class JwtRequest {

	String email_id;
	String password;

	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public JwtRequest(String email_id, String password) {
		super();
		this.email_id = email_id;
		this.password = password;
	}

	public JwtRequest() {}

	@Override
	public String toString() {
		return "JwtRequest [email_id=" + email_id + ", password=" + password + "]";
	}	
	
	
}
