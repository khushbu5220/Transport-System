package com.csir.vehiclerequisition.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Divisions 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String division_name;
	private Long user_id;
	private String status;

	public Divisions() {}

	public Divisions(Long id, String division_name, Long user_id, String status) {
		super();
		this.id = id;
		this.division_name = division_name;
		this.user_id = user_id;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDivision_name() {
		return division_name;
	}

	public void setDivision_name(String division_name) {
		this.division_name = division_name;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Divisions [id=" + id + ", division_name=" + division_name + ", user_id=" + user_id + ", status="
				+ status + "]";
	}
}
