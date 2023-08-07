package com.csir.vehiclerequisition.response;

import java.util.List;

import com.csir.vehiclerequisition.entities.NavbarMenu;

public class NavbarMenuResponse 
{
	private List<NavbarMenu> navbarmenu;
	private String username;
	public NavbarMenuResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public NavbarMenuResponse(List<NavbarMenu> navbarmenu, String username) {
		super();
		this.navbarmenu = navbarmenu;
		this.username = username;
	}
	public List<NavbarMenu> getNavbarmenu() {
		return navbarmenu;
	}
	public void setNavbarmenu(List<NavbarMenu> navbarmenu) {
		this.navbarmenu = navbarmenu;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String toString() {
		return "NavbarMenuResponse [navbarmenu=" + navbarmenu + ", username=" + username + "]";
	}
}
