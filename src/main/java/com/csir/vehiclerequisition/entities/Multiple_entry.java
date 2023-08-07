package com.csir.vehiclerequisition.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Multiple_entry 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String officer_name;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate date;
	private String from_time; //pickup
	private String to_time; //drop
	private LocalTime time;
	private String flight_no;
	private Long eid;
	private String status;
	private Date cdt;
	private Long session_id;
	private Long userid;
	private String designation_name;
	public Multiple_entry() {}
	public Multiple_entry(Long id, String officer_name, LocalDate date, String from_time, String to_time,
			LocalTime time, String flight_no, Long eid, String status, Date cdt, Long session_id, Long userid,
			String designation_name) {
		super();
		this.id = id;
		this.officer_name = officer_name;
		this.date = date;
		this.from_time = from_time;
		this.to_time = to_time;
		this.time = time;
		this.flight_no = flight_no;
		this.eid = eid;
		this.status = status;
		this.cdt = cdt;
		this.session_id = session_id;
		this.userid = userid;
		this.designation_name = designation_name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOfficer_name() {
		return officer_name;
	}
	public void setOfficer_name(String officer_name) {
		this.officer_name = officer_name;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getFrom_time() {
		return from_time;
	}
	public void setFrom_time(String from_time) {
		this.from_time = from_time;
	}
	public String getTo_time() {
		return to_time;
	}
	public void setTo_time(String to_time) {
		this.to_time = to_time;
	}
	public LocalTime getTime() {
		return time;
	}
	public void setTime(LocalTime time) {
		this.time = time;
	}
	public String getFlight_no() {
		return flight_no;
	}
	public void setFlight_no(String flight_no) {
		this.flight_no = flight_no;
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
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public String getDesignation_name() {
		return designation_name;
	}
	public void setDesignation_name(String designation_name) {
		this.designation_name = designation_name;
	}
	@Override
	public String toString() {
		return "Multiple_entry [id=" + id + ", officer_name=" + officer_name + ", date=" + date + ", from_time="
				+ from_time + ", to_time=" + to_time + ", time=" + time + ", flight_no=" + flight_no + ", eid=" + eid
				+ ", status=" + status + ", cdt=" + cdt + ", session_id=" + session_id + ", userid=" + userid
				+ ", designation_name=" + designation_name + "]";
	}
}
