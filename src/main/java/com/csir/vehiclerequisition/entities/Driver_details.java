package com.csir.vehiclerequisition.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Driver_details 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String driver_name;
	private Long vid;
	private String status;
	private Date cdt;
	private String session_id;
	public Driver_details() {}
	public Driver_details(Long id, String driver_name, Long vid, String status, Date cdt, String session_id) {
		super();
		this.id = id;
		this.driver_name = driver_name;
		this.vid = vid;
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
	public String getDriver_name() {
		return driver_name;
	}
	public void setDriver_name(String driver_name) {
		this.driver_name = driver_name;
	}
	public Long getVid() {
		return vid;
	}
	public void setVid(Long vid) {
		this.vid = vid;
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
		return "Driver_details [id=" + id + ", driver_name=" + driver_name + ", vid=" + vid + ", status=" + status
				+ ", cdt=" + cdt + ", session_id=" + session_id + "]";
	}
	
}
