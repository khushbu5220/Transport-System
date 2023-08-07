package com.csir.vehiclerequisition.response;

import com.csir.vehiclerequisition.entities.Divisions;

public class DivisionListResponse 
{
	private Divisions divisions;
	private String username;
	public DivisionListResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DivisionListResponse(Divisions divisions, String username) {
		super();
		this.divisions = divisions;
		this.username = username;
	}
	public Divisions getDivisions() {
		return divisions;
	}
	public void setDivisions(Divisions divisions) {
		this.divisions = divisions;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String toString() {
		return "DivisionListResponse [divisions=" + divisions + ", username=" + username + "]";
	}
}
