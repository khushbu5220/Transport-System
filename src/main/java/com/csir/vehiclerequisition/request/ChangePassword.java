package com.csir.vehiclerequisition.request;

public class ChangePassword 
{
	private String currentPassword;
	private String newPassword;
	public ChangePassword() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ChangePassword(String currentPassword, String newPassword) {
		super();
		this.currentPassword = currentPassword;
		this.newPassword = newPassword;
	}
	public String getCurrentPassword() {
		return currentPassword;
	}
	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	@Override
	public String toString() {
		return "ChangePassword [currentPassword=" + currentPassword + ", newPassword=" + newPassword + "]";
	}
}
