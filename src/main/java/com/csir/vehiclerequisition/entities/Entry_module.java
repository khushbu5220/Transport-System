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
public class Entry_module 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String designation_name;
	private String division_name;
	private String email_id;
	private String mobile;
	private String extension;
	private String purpose;
	private String from_destination;
	private String to_destination;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate  date;
	private LocalTime to_time;
	private LocalTime till_time;
	private String status;
	private Date  cdt;
	private Long session_id;
	private Long userid;
	public Entry_module() {}
	public Entry_module(Long id, String name, String designation_name, String division_name, String email_id,
			String mobile, String extension, String purpose, String from_destination, String to_destination,
			LocalDate date, LocalTime to_time, LocalTime till_time, String status, Date cdt, Long session_id,
			Long userid) {
		super();
		this.id = id;
		this.name = name;
		this.designation_name = designation_name;
		this.division_name = division_name;
		this.email_id = email_id;
		this.mobile = mobile;
		this.extension = extension;
		this.purpose = purpose;
		this.from_destination = from_destination;
		this.to_destination = to_destination;
		this.date = date;
		this.to_time = to_time;
		this.till_time = till_time;
		this.status = status;
		this.cdt = cdt;
		this.session_id = session_id;
		this.userid = userid;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesignation_name() {
		return designation_name;
	}
	public void setDesignation_name(String designation_name) {
		this.designation_name = designation_name;
	}
	public String getDivision_name() {
		return division_name;
	}
	public void setDivision_name(String division_name) {
		this.division_name = division_name;
	}
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getFrom_destination() {
		return from_destination;
	}
	public void setFrom_destination(String from_destination) {
		this.from_destination = from_destination;
	}
	public String getTo_destination() {
		return to_destination;
	}
	public void setTo_destination(String to_destination) {
		this.to_destination = to_destination;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public LocalTime getTo_time() {
		return to_time;
	}
	public void setTo_time(LocalTime to_time) {
		this.to_time = to_time;
	}
	public LocalTime getTill_time() {
		return till_time;
	}
	public void setTill_time(LocalTime till_time) {
		this.till_time = till_time;
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
	@Override
	public String toString() {
		return "Entry_module [id=" + id + ", name=" + name + ", designation_name=" + designation_name
				+ ", division_name=" + division_name + ", email_id=" + email_id + ", mobile=" + mobile + ", extension="
				+ extension + ", purpose=" + purpose + ", from_destination=" + from_destination + ", to_destination="
				+ to_destination + ", date=" + date + ", to_time=" + to_time + ", till_time=" + till_time + ", status="
				+ status + ", cdt=" + cdt + ", session_id=" + session_id + ", userid=" + userid + "]";
	}
}
