package com.csir.vehiclerequisition.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Users 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String display_name;
	private String email_id;
	private String Password;
	private String status;
	private String role;
	private Date cdt;
	private String sessionid;
	private String division_name; 
	
	public Users() {}

	public Users(Long id, String display_name, String email_id, String password, String status, String role, Date cdt,
			String sessionid, String division_name) {
		super();
		this.id = id;
		this.display_name = display_name;
		this.email_id = email_id;
		Password = password;
		this.status = status;
		this.role = role;
		this.cdt = cdt;
		this.sessionid = sessionid;
		this.division_name = division_name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDisplay_name() {
		return display_name;
	}

	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}

	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Date getCdt() {
		return cdt;
	}

	public void setCdt(Date cdt) {
		this.cdt = cdt;
	}

	public String getSessionid() {
		return sessionid;
	}

	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}

	public String getDivision_name() {
		return division_name;
	}

	public void setDivision_name(String division_name) {
		this.division_name = division_name;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", display_name=" + display_name + ", email_id=" + email_id + ", Password="
				+ Password + ", status=" + status + ", role=" + role + ", cdt=" + cdt + ", sessionid=" + sessionid
				+ ", division_name=" + division_name + "]";
	}

}
