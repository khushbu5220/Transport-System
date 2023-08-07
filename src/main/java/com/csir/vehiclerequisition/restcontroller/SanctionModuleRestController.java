package com.csir.vehiclerequisition.restcontroller;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.csir.vehiclerequisition.config.JwtAuthenticationFilter;
import com.csir.vehiclerequisition.controller.JwtController;
import com.csir.vehiclerequisition.entities.Entry_module;
import com.csir.vehiclerequisition.entities.Multiple_entry;
import com.csir.vehiclerequisition.entities.Sanctioning_authority;
import com.csir.vehiclerequisition.entities.Users;
import com.csir.vehiclerequisition.repositories.DesignationsRepository;
import com.csir.vehiclerequisition.repositories.DivisionsRepository;
import com.csir.vehiclerequisition.repositories.Entry_moduleRepository;
import com.csir.vehiclerequisition.repositories.Multiple_entryRepository;
import com.csir.vehiclerequisition.repositories.Sanctioning_authorityRepository;
import com.csir.vehiclerequisition.repositories.Transport_cellRepository;
import com.csir.vehiclerequisition.repositories.UsersRepository;
import com.csir.vehiclerequisition.request.FilterDataRequest;
import com.csir.vehiclerequisition.request.RequisitionSlipResponse;
import com.csir.vehiclerequisition.request.StatusRequest;
import com.csir.vehiclerequisition.response.BookingListResponse;

@CrossOrigin("*")
@RestController
public class SanctionModuleRestController 
{
	@Autowired
	JwtAuthenticationFilter JwtAuthentication;
	@Autowired
	UsersRepository userrepository;
	@Autowired
	Entry_moduleRepository entrymodulerepository;
	@Autowired
	Multiple_entryRepository multipleentryrepository;
	@Autowired
	DesignationsRepository designationsrepository;
	@Autowired
	DivisionsRepository divisionrepository;
	@Autowired
	Sanctioning_authorityRepository sanctioningauthorityrepository;
	@Autowired
	Transport_cellRepository transportcellrepository;
	
	@GetMapping("/GetSanctioningRequestList")
	public BookingListResponse GetSanctioningRequestList()
	{
		BookingListResponse ret = new BookingListResponse();
		try
		{
			String name = JwtAuthentication.checkname();
			Users user = userrepository.findByEmailID(name);
			
			String division = divisionrepository.findDivision(user.getId());
			
			List<Entry_module> entry_module = entrymodulerepository.findByStatus("Saved", division);
			Long tot_booking = (long) entry_module.size();
			Long pending_booking = entrymodulerepository.countByDiv("Submitted", division);
			List<Entry_module> em = entrymodulerepository.findByStatus("Approved", "Allocated", "Not allocated");
			
			Long approved_booking = (long) em.size();
			Long allocated_booking = entrymodulerepository.countByDiv("Allocated", division);
			ret.setTot_booking(tot_booking);
			ret.setPending_booking(pending_booking);
			ret.setApproved_booking(approved_booking);
			ret.setAllocated_booking(allocated_booking);
			ret.setEntry_module(entry_module);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return ret;
	}
	
	@GetMapping("/GetSanctioningRequest/{Id}")
	public RequisitionSlipResponse GetSanctioningRequest(@PathVariable("Id") Long Id)
	{
		RequisitionSlipResponse ret = new RequisitionSlipResponse();
		try
		{
			String name = JwtAuthentication.checkname();
			Users user = userrepository.findByEmailID(name);
			
			@SuppressWarnings("deprecation")
			Entry_module entry_module = entrymodulerepository.getById(Id);
			List<Multiple_entry> multiple_entry = multipleentryrepository.findByEid(entry_module.getId());
			ret.setEntry_module(entry_module);
			ret.setMultiple_entry(multiple_entry);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return ret;
	}
	
	@PutMapping("/updateSanctionedRequest/{Id}")
	public int updateSanctionedRequest(@PathVariable("Id") Long Id, @RequestBody StatusRequest statusrequest)
	{
		int ret = 0;
		try
		{
			String name = JwtAuthentication.checkname();
			Users user = userrepository.findByEmailID(name);
			
			ret = entrymodulerepository.updateStatus(statusrequest.getStatus(), Id);
			
			Sanctioning_authority sa = new Sanctioning_authority();
			sa.setCdt(new Date());
			sa.setEid(Id);
			sa.setRemarks("");
			sa.setSession_id(JwtController.session_id);
			sa.setStatus("Active");
			sanctioningauthorityrepository.save(sa);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return ret;
	}
	
	@PostMapping("/filterSanctionData")
	public BookingListResponse filterData(@RequestBody FilterDataRequest filterrequest)
	{
		BookingListResponse ret = new BookingListResponse();
		try
		{
			String EmailId = JwtAuthentication.checkname();
			Users user = userrepository.findByEmailID(EmailId);
			
			String division = divisionrepository.findDivision(user.getId());
			
			if(filterrequest.getTo_date()  != null || filterrequest.getFrom_date() != null)
			{
				Date fromDate = null;
				Date toDate = null;
				if(filterrequest.getFrom_date() != null)
				{
					fromDate = Date.from(filterrequest.getFrom_date().atStartOfDay(ZoneId.systemDefault()).toInstant());
				}
				if(filterrequest.getTo_date() != null)
				{
					toDate = Date.from(filterrequest.getTo_date().atStartOfDay(ZoneId.systemDefault()).toInstant());
				}
				
				List<Entry_module> entry_module = entrymodulerepository.filterSanctionData(division, fromDate, toDate);
				Long pending_booking = 0L;
				Long approved_booking = 0L;
				Long allocated_booking = 0L;
				Long tot_booking = (long) entry_module.size();
				for(Entry_module e : entry_module)
				{
					if("Submitted".equalsIgnoreCase(e.getStatus()))
					{
						pending_booking += 1L;
					}
					else if("Allocated".equalsIgnoreCase(e.getStatus()) || "Approved".equalsIgnoreCase(e.getStatus()) || "Not allocated".equalsIgnoreCase(e.getStatus()))
					{
						approved_booking += 1L;
					}
				}
				ret.setPending_booking(pending_booking);
				ret.setApproved_booking(approved_booking);
				ret.setAllocated_booking(allocated_booking);
				ret.setTot_booking(tot_booking);
				ret.setEntry_module(entry_module);
			}
			else
			{
				List<Entry_module> entry_module = entrymodulerepository.findByStatus("Saved", division);
				Long tot_booking = (long) entry_module.size();
				Long pending_booking = entrymodulerepository.countByDiv("Submitted", division);
				List<Entry_module> em = entrymodulerepository.findByStatus("Approved", "Allocated", "Not allocated");
				
				Long approved_booking = (long) em.size();
				Long allocated_booking = entrymodulerepository.countByDiv("Allocated", division);
				ret.setTot_booking(tot_booking);
				ret.setPending_booking(pending_booking);
				ret.setApproved_booking(approved_booking);
				ret.setAllocated_booking(allocated_booking);
				ret.setEntry_module(entry_module);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return ret;
	}
}
