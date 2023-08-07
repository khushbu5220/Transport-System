package com.csir.vehiclerequisition.request;

public class StatusRequest 
{
	private String status;

	public StatusRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StatusRequest(String status) {
		super();
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "StatusRequest [status=" + status + "]";
	}
}
