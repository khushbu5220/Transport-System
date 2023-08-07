package com.csir.vehiclerequisition.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Designations 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String designation_name;
	private String division_id;
	public Designations() {}
	public Designations(Long id, String designation_name, String division_id) {
		super();
		this.id = id;
		this.designation_name = designation_name;
		this.division_id = division_id;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDesignation_name() {
		return designation_name;
	}
	public void setDesignation_name(String designation_name) {
		this.designation_name = designation_name;
	}
	public String getDivision_id() {
		return division_id;
	}
	public void setDivision_id(String division_id) {
		this.division_id = division_id;
	}
	@Override
	public String toString() {
		return "Designations [id=" + id + ", designation_name=" + designation_name + ", division_id=" + division_id
				+ "]";
	}
}
