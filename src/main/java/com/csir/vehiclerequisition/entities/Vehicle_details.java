package com.csir.vehiclerequisition.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Vehicle_details 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String vehicle_no;
	private String status;
	private Date cdt;
	private String session_id;
	public Vehicle_details() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Vehicle_details(Long id, String vehicle_no, String status, Date cdt, String session_id) {
		super();
		this.id = id;
		this.vehicle_no = vehicle_no;
		this.status = status;
		this.cdt = cdt;
		this.session_id = session_id;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getVehicle_no() {
		return vehicle_no;
	}
	public void setVehicle_no(String vehicle_no) {
		this.vehicle_no = vehicle_no;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCdt() {
		return cdt;
	}
	public void setCdt(Date cdt) {
		this.cdt = cdt;
	}
	public String getSession_id() {
		return session_id;
	}
	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}
	@Override
	public String toString() {
		return "Vehicle_details [id=" + id + ", vehicle_no=" + vehicle_no + ", status=" + status + ", cdt=" + cdt
				+ ", session_id=" + session_id + "]";
	}
}
