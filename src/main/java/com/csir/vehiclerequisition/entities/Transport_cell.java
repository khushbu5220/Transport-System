package com.csir.vehiclerequisition.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Transport_cell 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private Long eid;
	private String vehicle_id;
	private Date cdt;
	private String status;
	private Long session_id;
	public Transport_cell() {}
	public Transport_cell(Long id, Long eid, String vehicle_id, Date cdt, String status, Long session_id) {
		super();
		this.id = id;
		this.eid = eid;
		this.vehicle_id = vehicle_id;
		this.cdt = cdt;
		this.status = status;
		this.session_id = session_id;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getEid() {
		return eid;
	}
	public void setEid(Long eid) {
		this.eid = eid;
	}
	public String getVehicle_id() {
		return vehicle_id;
	}
	public void setVehicle_id(String vehicle_id) {
		this.vehicle_id = vehicle_id;
	}
	public Date getCdt() {
		return cdt;
	}
	public void setCdt(Date cdt) {
		this.cdt = cdt;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getSession_id() {
		return session_id;
	}
	public void setSession_id(Long session_id) {
		this.session_id = session_id;
	}
	@Override
	public String toString() {
		return "Transport_cell [id=" + id + ", eid=" + eid + ", vehicle_id=" + vehicle_id + ", cdt=" + cdt + ", status="
				+ status + ", session_id=" + session_id + "]";
	}
}
