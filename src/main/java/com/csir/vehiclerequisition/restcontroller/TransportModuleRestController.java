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
import com.csir.vehiclerequisition.entities.Transport_cell;
import com.csir.vehiclerequisition.entities.Users;
import com.csir.vehiclerequisition.repositories.DesignationsRepository;
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
public class TransportModuleRestController 
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
	Sanctioning_authorityRepository sanctioningauthorityrepository;
	@Autowired
	Transport_cellRepository transportcellrepository;
	
	@GetMapping("/GetTransportationRequest")
	public BookingListResponse GetSanctioningRequest()
	{
		BookingListResponse ret = new BookingListResponse();
		try
		{
			String name = JwtAuthentication.checkname();
			Users user = userrepository.findByEmailID(name);
			
			List<Entry_module> entry_module = entrymodulerepository.findByStatus("Approved", "Allocated", "Not allocated");
			System.out.println("entry_module : "+entry_module);
			Long pending_booking = (long) entrymodulerepository.countlist("Approved").size();
			Long approved_booking = (long) entrymodulerepository.countlist("Allocated").size();
			Long tot_booking = (long) entry_module.size();
			ret.setTot_booking(tot_booking);
			ret.setPending_booking(pending_booking);
			ret.setApproved_booking(approved_booking);
			ret.setEntry_module(entry_module);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return ret;
	}
	
	@GetMapping("/ViewEntryModuleList/transport_pending_request")
	public List<Entry_module> Transport_Pending_Request()
	{
		List<Entry_module> ret = null;
		try
		{
			String name = JwtAuthentication.checkname();
			Users user = userrepository.findByEmailID(name);
			ret = entrymodulerepository.countlist("Approved");
		}
		catch(Exception e)
		{
			e.printStackTrace(); 
		}
		return ret;
	}
	
	@GetMapping("/ViewEntryModuleList/transport_approved_request")
	public List<Entry_module> Transport_approved_Request()
	{
		List<Entry_module> ret = null;
		try
		{
			String name = JwtAuthentication.checkname();
			Users user = userrepository.findByEmailID(name);
			ret = entrymodulerepository.countlist("Allocated");
		}
		catch(Exception e)
		{
			e.printStackTrace(); 
		}
		return ret;
	}
	
	@GetMapping("/GetAllocatingRequest/{Id}")
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
	
	@PutMapping("/updateAllocatedRequest/{Id}")
	public int updateSanctionedRequest(@PathVariable("Id") Long Id, @RequestBody StatusRequest statusrequest)
	{
		int ret = 0;
		try
		{
			String name = JwtAuthentication.checkname();
			Users user = userrepository.findByEmailID(name);
			
			ret = entrymodulerepository.updateStatus(statusrequest.getStatus(), Id);
			
			Transport_cell tc = new Transport_cell();
			tc.setCdt(new Date());
			tc.setEid(Id);
			tc.setSession_id(JwtController.session_id);
			tc.setStatus("Active");
			tc.setVehicle_id("");
			transportcellrepository.save(tc);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return ret;
	}
	
	@PostMapping("/filterData")
	public BookingListResponse filterData(@RequestBody FilterDataRequest filterrequest)
	{
		BookingListResponse ret = new BookingListResponse();
		try
		{
			System.out.println("----inside filter data----");
//			System.out.println("filterrequest : "+filterrequest);
			System.out.println(filterrequest.getFrom_date());
			System.out.println(filterrequest.getTo_date());
			if(!"".equalsIgnoreCase(filterrequest.getDivision()) || filterrequest.getTo_date()  != null || filterrequest.getFrom_date() != null)
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
				
				List<Entry_module> entry_module = entrymodulerepository.filterData("Approved", "Allocated", "Not allocated", filterrequest.getDivision(), fromDate, toDate);
				Long pending_booking = 0L;
				Long approved_booking = 0L;
				Long tot_booking = (long) entry_module.size();
				for(Entry_module e : entry_module)
				{
					if("Approved".equalsIgnoreCase(e.getStatus()))
					{
						pending_booking += 1L;
					}
					else if("Allocated".equalsIgnoreCase(e.getStatus()))
					{
						approved_booking += 1L;
					}
				}
				ret.setPending_booking(pending_booking);
				ret.setApproved_booking(approved_booking);
				ret.setTot_booking(tot_booking);
				ret.setEntry_module(entry_module);
			}
			else
			{
				List<Entry_module> entry_module = entrymodulerepository.findByStatus("Approved", "Allocated", "Not allocated");
				Long pending_booking = (long) entrymodulerepository.countlist("Approved").size();
				Long approved_booking = (long) entrymodulerepository.countlist("Allocated").size();
				Long tot_booking = (long) entry_module.size();
				ret.setTot_booking(tot_booking);
				ret.setPending_booking(pending_booking);
				ret.setApproved_booking(approved_booking);
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
