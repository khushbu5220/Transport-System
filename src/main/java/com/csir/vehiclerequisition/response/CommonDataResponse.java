package com.csir.vehiclerequisition.response;

import java.util.List;

import com.csir.vehiclerequisition.entities.Designations;
import com.csir.vehiclerequisition.entities.Divisions;
import com.csir.vehiclerequisition.entities.Users;

public class CommonDataResponse 
{
	private Users users;
	private List<Designations> designations;
	private List<Divisions> divisions;
	public CommonDataResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CommonDataResponse(Users users, List<Designations> designations, List<Divisions> divisions) {
		super();
		this.users = users;
		this.designations = designations;
		this.divisions = divisions;
	}
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}
	public List<Designations> getDesignations() {
		return designations;
	}
	public void setDesignations(List<Designations> designations) {
		this.designations = designations;
	}
	public List<Divisions> getDivisions() {
		return divisions;
	}
	public void setDivisions(List<Divisions> divisions) {
		this.divisions = divisions;
	}
	@Override
	public String toString() {
		return "CommonDataResponse [users=" + users + ", designations=" + designations + ", divisions=" + divisions
				+ "]";
	}
}
