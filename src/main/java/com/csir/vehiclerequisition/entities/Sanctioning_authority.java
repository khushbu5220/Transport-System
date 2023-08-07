package com.csir.vehiclerequisition.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Sanctioning_authority 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private Long eid;
	private String status;
	private Date cdt;
	private Long session_id;
	private String remarks;
	public Sanctioning_authority() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Sanctioning_authority(Long id, Long eid, String status, Date cdt, Long session_id, String remarks) {
		super();
		this.id = id;
		this.eid = eid;
		this.status = status;
		this.cdt = cdt;
		this.session_id = session_id;
		this.remarks = remarks;
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
	public Long getSession_id() {
		return session_id;
	}
	public void setSession_id(Long session_id) {
		this.session_id = session_id;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Override
	public String toString() {
		return "Sanctioning_authority [id=" + id + ", eid=" + eid + ", status=" + status + ", cdt=" + cdt
				+ ", session_id=" + session_id + ", remarks=" + remarks + "]";
	}
	
}
