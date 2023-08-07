package com.csir.vehiclerequisition.request;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class FilterDataRequest 
{
	private String division;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate from_date;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate to_date;
	public FilterDataRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FilterDataRequest(String division, LocalDate from_date, LocalDate to_date) {
		super();
		this.division = division;
		this.from_date = from_date;
		this.to_date = to_date;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public LocalDate getFrom_date() {
		return from_date;
	}
	public void setFrom_date(LocalDate from_date) {
		this.from_date = from_date;
	}
	public LocalDate getTo_date() {
		return to_date;
	}
	public void setTo_date(LocalDate to_date) {
		this.to_date = to_date;
	}
	@Override
	public String toString() {
		return "FilterDataRequest [division=" + division + ", from_date=" + from_date + ", to_date=" + to_date + "]";
	}
}
