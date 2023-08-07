package com.csir.vehiclerequisition.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class NavbarMenu 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String navbar_name;
	private String foruserrole;
	private String navbar_url;
	private String status;
	public NavbarMenu() {
		super();
		// TODO Auto-generated constructor stub
	}
	public NavbarMenu(Long id, String navbar_name, String foruserrole, String navbar_url, String status) {
		super();
		this.id = id;
		this.navbar_name = navbar_name;
		this.foruserrole = foruserrole;
		this.navbar_url = navbar_url;
		this.status = status;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNavbar_name() {
		return navbar_name;
	}
	public void setNavbar_name(String navbar_name) {
		this.navbar_name = navbar_name;
	}
	public String getForuserrole() {
		return foruserrole;
	}
	public void setForuserrole(String foruserrole) {
		this.foruserrole = foruserrole;
	}
	public String getNavbar_url() {
		return navbar_url;
	}
	public void setNavbar_url(String navbar_url) {
		this.navbar_url = navbar_url;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "NavbarMenu [id=" + id + ", navbar_name=" + navbar_name + ", foruserrole=" + foruserrole
				+ ", navbar_url=" + navbar_url + ", status=" + status + "]";
	}
}
