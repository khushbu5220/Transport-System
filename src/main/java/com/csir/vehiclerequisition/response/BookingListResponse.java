package com.csir.vehiclerequisition.response;

import java.util.List;

import com.csir.vehiclerequisition.entities.Entry_module;

public class BookingListResponse 
{
	private Long tot_booking;
	private Long approved_booking;
	private Long pending_booking;
	private Long allocated_booking;
	private List<Entry_module> entry_module;
	public BookingListResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BookingListResponse(Long tot_booking, Long approved_booking, Long pending_booking, Long allocated_booking,
			List<Entry_module> entry_module) {
		super();
		this.tot_booking = tot_booking;
		this.approved_booking = approved_booking;
		this.pending_booking = pending_booking;
		this.allocated_booking = allocated_booking;
		this.entry_module = entry_module;
	}
	public Long getTot_booking() {
		return tot_booking;
	}
	public void setTot_booking(Long tot_booking) {
		this.tot_booking = tot_booking;
	}
	public Long getApproved_booking() {
		return approved_booking;
	}
	public void setApproved_booking(Long approved_booking) {
		this.approved_booking = approved_booking;
	}
	public Long getPending_booking() {
		return pending_booking;
	}
	public void setPending_booking(Long pending_booking) {
		this.pending_booking = pending_booking;
	}
	public Long getAllocated_booking() {
		return allocated_booking;
	}
	public void setAllocated_booking(Long allocated_booking) {
		this.allocated_booking = allocated_booking;
	}
	public List<Entry_module> getEntry_module() {
		return entry_module;
	}
	public void setEntry_module(List<Entry_module> entry_module) {
		this.entry_module = entry_module;
	}
	@Override
	public String toString() {
		return "BookingListResponse [tot_booking=" + tot_booking + ", approved_booking=" + approved_booking
				+ ", pending_booking=" + pending_booking + ", allocated_booking=" + allocated_booking
				+ ", entry_module=" + entry_module + "]";
	}
}
