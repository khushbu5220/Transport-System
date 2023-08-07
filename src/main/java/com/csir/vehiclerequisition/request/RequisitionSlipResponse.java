package com.csir.vehiclerequisition.request;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.csir.vehiclerequisition.entities.Entry_module;
import com.csir.vehiclerequisition.entities.Multiple_entry;
import com.fasterxml.jackson.annotation.JsonFormat;

public class RequisitionSlipResponse 
{
	private Entry_module entry_module;
	private List<Multiple_entry> multiple_entry;
	public RequisitionSlipResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RequisitionSlipResponse(Entry_module entry_module, List<Multiple_entry> multiple_entry) {
		super();
		this.entry_module = entry_module;
		this.multiple_entry = multiple_entry;
	}
	public Entry_module getEntry_module() {
		return entry_module;
	}
	public void setEntry_module(Entry_module entry_module) {
		this.entry_module = entry_module;
	}
	public List<Multiple_entry> getMultiple_entry() {
		return multiple_entry;
	}
	public void setMultiple_entry(List<Multiple_entry> multiple_entry) {
		this.multiple_entry = multiple_entry;
	}
	@Override
	public String toString() {
		return "RequisitionSlipResponse [entry_module=" + entry_module + ", multiple_entry=" + multiple_entry + "]";
	}
}
