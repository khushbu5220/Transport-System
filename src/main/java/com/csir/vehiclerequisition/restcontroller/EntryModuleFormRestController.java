package com.csir.vehiclerequisition.restcontroller;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.csir.vehiclerequisition.config.JwtAuthenticationFilter;
import com.csir.vehiclerequisition.controller.JwtController;
import com.csir.vehiclerequisition.entities.Designations;
import com.csir.vehiclerequisition.entities.Entry_module;
import com.csir.vehiclerequisition.entities.Multiple_entry;
import com.csir.vehiclerequisition.entities.Sanctioning_authority;
import com.csir.vehiclerequisition.entities.Transport_cell;
import com.csir.vehiclerequisition.entities.Users;
import com.csir.vehiclerequisition.helper.CustomUserDetails;
import com.csir.vehiclerequisition.repositories.DesignationsRepository;
import com.csir.vehiclerequisition.repositories.DivisionsRepository;
import com.csir.vehiclerequisition.repositories.Entry_moduleRepository;
import com.csir.vehiclerequisition.repositories.Multiple_entryRepository;
import com.csir.vehiclerequisition.repositories.Sanctioning_authorityRepository;
import com.csir.vehiclerequisition.repositories.Transport_cellRepository;
import com.csir.vehiclerequisition.repositories.UsersRepository;
import com.csir.vehiclerequisition.request.FilterDataRequest;
import com.csir.vehiclerequisition.request.RequisitionSlipResponse;
import com.csir.vehiclerequisition.response.BookingListResponse;
import com.csir.vehiclerequisition.services.CustomUserDetailsService;

@CrossOrigin("*")
@RestController
public class EntryModuleFormRestController 
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
	@Autowired
	DivisionsRepository divisionsrepository;
	@Autowired
	CustomUserDetailsService customuserdetailsservice;
	
	@GetMapping("/getDesignation")
	public List<Designations> getDesignation()
	{
		List<Designations> ret = null;
		try
		{
			ret = designationsrepository.findAll();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return ret;
	}
	
	@PostMapping("/saveEntryModuleForm")
	public String saveEntryModuleForm(@RequestBody RequisitionSlipResponse requisitionslipresponse)
	{
		String ret = "";
		ResponseEntity<?> res = null;
		try
		{
//			System.out.println(requisitionslipresponse.toString());
			String name = JwtAuthentication.checkname();
			Users user = userrepository.findByEmailID(name);
		
			Entry_module em = new Entry_module();
			if(requisitionslipresponse.getEntry_module().getId() != 0L)
			{
				em.setId(requisitionslipresponse.getEntry_module().getId());
				int i = multipleentryrepository.deleteByEid(requisitionslipresponse.getEntry_module().getId());
				System.out.println("i :"+i);
			}
			em.setName(requisitionslipresponse.getEntry_module().getName());
			if("".equalsIgnoreCase(requisitionslipresponse.getEntry_module().getDesignation_name()))
			{
				res = ResponseEntity.status(400).body("Designation is required");
				return String.valueOf(res.getStatusCodeValue())+"|"+res.getBody();
			}
			em.setDesignation_name(requisitionslipresponse.getEntry_module().getDesignation_name());
			if("".equalsIgnoreCase(requisitionslipresponse.getEntry_module().getDivision_name()))
			{
				res = ResponseEntity.status(400).body("Division is required");
				return String.valueOf(res.getStatusCodeValue())+"|"+res.getBody();
			}
			em.setDivision_name(requisitionslipresponse.getEntry_module().getDivision_name());
			if("".equalsIgnoreCase(requisitionslipresponse.getEntry_module().getPurpose()))
			{
				res = ResponseEntity.status(400).body("Purpose is required");
				return String.valueOf(res.getStatusCodeValue())+"|"+res.getBody();
			}
			em.setPurpose(requisitionslipresponse.getEntry_module().getPurpose());
			if("".equalsIgnoreCase(requisitionslipresponse.getEntry_module().getFrom_destination()))
			{
				res = ResponseEntity.status(400).body("From Destination is required");
				return String.valueOf(res.getStatusCodeValue())+"|"+res.getBody();
			}
			em.setFrom_destination(requisitionslipresponse.getEntry_module().getFrom_destination());
			if("".equalsIgnoreCase(requisitionslipresponse.getEntry_module().getTo_destination()))
			{
				res = ResponseEntity.status(400).body("To Destination is required");
				return String.valueOf(res.getStatusCodeValue())+"|"+res.getBody();
			}
			em.setTo_destination(requisitionslipresponse.getEntry_module().getTo_destination());
			if("".equalsIgnoreCase(requisitionslipresponse.getEntry_module().getMobile()))
			{
				res = ResponseEntity.status(400).body("Mobile No is required");
				return String.valueOf(res.getStatusCodeValue())+"|"+res.getBody();
			}
			em.setMobile(requisitionslipresponse.getEntry_module().getMobile());
			if("".equalsIgnoreCase(requisitionslipresponse.getEntry_module().getExtension()))
			{
				res = ResponseEntity.status(400).body("Extension is required");
				return String.valueOf(res.getStatusCodeValue())+"|"+res.getBody();
			}
			em.setExtension(requisitionslipresponse.getEntry_module().getExtension());
			if(requisitionslipresponse.getEntry_module().getDate() == null)
			{
				res = ResponseEntity.status(400).body("Date is required");
				return String.valueOf(res.getStatusCodeValue())+"|"+res.getBody();
			}
			em.setDate(requisitionslipresponse.getEntry_module().getDate());
			if(requisitionslipresponse.getEntry_module().getTo_time() == null)
			{
				res = ResponseEntity.status(400).body("To time is required");
				return String.valueOf(res.getStatusCodeValue())+"|"+res.getBody();
			}
			em.setTo_time(requisitionslipresponse.getEntry_module().getTo_time());
			if(requisitionslipresponse.getEntry_module().getTill_time() == null)
			{
				res = ResponseEntity.status(400).body("Till time is required");
				return String.valueOf(res.getStatusCodeValue())+"|"+res.getBody();
			}
			em.setTill_time(requisitionslipresponse.getEntry_module().getTill_time());
			em.setEmail_id(user.getEmail_id());
			em.setStatus("Saved");
			em.setCdt(new Date());
			em.setSession_id(JwtController.session_id);
			em.setUserid(user.getId());
			Entry_module entry_module = entrymodulerepository.save(em);
			System.out.println("entry_module :"+ entry_module.toString());
			for(Multiple_entry m : requisitionslipresponse.getMultiple_entry())
			{
				Multiple_entry multiple_entry = new Multiple_entry();
				multiple_entry.setEid(entry_module.getId());
//				if("".equalsIgnoreCase(m.getOfficer_name()))
//				{
//					res = ResponseEntity.status(400).body("Officer name in Entry Table is required");
//					return String.valueOf(res.getStatusCodeValue())+"|"+res.getBody();
//				}
				multiple_entry.setOfficer_name(m.getOfficer_name());
//				if(m.getDate() == null) 
//				{
//					res = ResponseEntity.status(400).body("Date in Entry Table is required");
//					return String.valueOf(res.getStatusCodeValue())+"|"+res.getBody();
//				}
				multiple_entry.setDate(m.getDate());
//				if(m.getTo_time() == null)
//				{
//					res = ResponseEntity.status(400).body("To Time in Entry Table is required");
//					return String.valueOf(res.getStatusCodeValue())+"|"+res.getBody();
//				}
				multiple_entry.setTo_time(m.getTo_time());
//				if(m.getFrom_time() == null)
//				{
//					res = ResponseEntity.status(400).body("From Time in Entry Table is required");
//					return String.valueOf(res.getStatusCodeValue())+"|"+res.getBody();
//				}
				multiple_entry.setFrom_time(m.getFrom_time());
//				if(m.getTime() == null)
//				{
//					res = ResponseEntity.status(400).body("Time in Entry Table is required");
//					return String.valueOf(res.getStatusCodeValue())+"|"+res.getBody();
//				}
				multiple_entry.setTime(m.getTime());
				multiple_entry.setFlight_no(m.getFlight_no());
				multiple_entry.setStatus("active");
				multiple_entry.setCdt(new Date());
				multiple_entry.setSession_id(JwtController.session_id);
				multiple_entry.setUserid(user.getId());
				multiple_entry.setDesignation_name(m.getDesignation_name());
				multipleentryrepository.save(multiple_entry);
			}
		
			res = ResponseEntity.status(201).body("Record saved Successfully");
			ret = String.valueOf(res.getStatusCodeValue())+"|"+res.getBody()+"|"+entry_module.getId();
		}
		catch(Exception e)
		{
			res = ResponseEntity.status(404).body("Record saved Unsuccessfully");
			ret = String.valueOf(res.getStatusCodeValue())+"|"+res.getBody();
			e.printStackTrace();
		}
		return ret;
	}
	
	@GetMapping("/ViewEntryModuleList")
	public BookingListResponse ViewEntryModuleList()
	{
		BookingListResponse ret = new BookingListResponse();
		try
		{
			String name = JwtAuthentication.checkname();
			Users user = userrepository.findByEmailID(name);
			System.out.println("-----------"+JwtController.session_id);
			List<Entry_module> entry_module = entrymodulerepository.findByUser_id(user.getId());
			Long tot_booking = (long) entry_module.size();
			List<Entry_module> pending_booking = entrymodulerepository.countByUserid("Submitted", user.getId());
			List<Entry_module> em = entrymodulerepository.findApproved(user.getId(), "Approved", "Allocated", "Not allocated");
			Long approved_booking = (long) em.size();
			List<Entry_module> allocated_booking = entrymodulerepository.countByUserid("Allocated", user.getId());
			ret.setTot_booking(tot_booking);
			ret.setPending_booking((long) pending_booking.size());
			ret.setApproved_booking(approved_booking);
			ret.setAllocated_booking((long) allocated_booking.size());
			ret.setEntry_module(entry_module);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return ret;
	}
	
	@GetMapping("/ViewEntryModuleList/users_pending_request")
	public List<Entry_module> Users_Pending_Request()
	{
		List<Entry_module> ret = null;
		try
		{
			String name = JwtAuthentication.checkname();
			Users user = userrepository.findByEmailID(name);
			
			ret = entrymodulerepository.countByUserid("Submitted", user.getId());
		}
		catch(Exception e)
		{
			e.printStackTrace(); 
		}
		return ret;
	}
	
	@GetMapping("/ViewEntryModuleList/users_approved_request")
	public List<Entry_module> Users_approved_Request()
	{
		List<Entry_module> ret = null;
		try
		{
			String name = JwtAuthentication.checkname();
			Users user = userrepository.findByEmailID(name);
			
			ret = entrymodulerepository.findApproved(user.getId(), "Approved", "Allocated", "Not allocated");
		}
		catch(Exception e)
		{
			e.printStackTrace(); 
		}
		return ret;
	}
	
	@GetMapping("/ViewEntryModuleList/users_allocated_request")
	public List<Entry_module> Users_allocated_Request()
	{
		List<Entry_module> ret = null;
		try
		{
			String name = JwtAuthentication.checkname();
			Users user = userrepository.findByEmailID(name);
			
			ret = entrymodulerepository.countByUserid("Allocated", user.getId());
		}
		catch(Exception e)
		{
			e.printStackTrace(); 
		}
		return ret;
	}
	
	@GetMapping("/GetEntryModuleData/{Id}")
	public Optional<Entry_module> GetEntryModuleData(@PathVariable("Id") Long Id)
	{
		Optional<Entry_module> ret = null;
		try
		{
			Optional<Entry_module> oneData = entrymodulerepository.findById(Id);
			ret = oneData;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return ret;
	}
	
	@PutMapping("/SubmitEntryModuleData/{Id}")
	public String SubmitEntryModuleData(@PathVariable("Id") Long Id)
	{
		String ret ="";
		ResponseEntity<?> res = null;
		try
		{
			String name = JwtAuthentication.checkname();
			Users user = userrepository.findByEmailID(name);
			
			if(user.getRole().equalsIgnoreCase("ROLE_HOD"))
			{
				if(customuserdetailsservice.isHOD(user.getId()))
				{
					entrymodulerepository.updateStatus("Approved", Id);
				}
				else
				{
					entrymodulerepository.updateStatus("Submitted", Id);
				}
			}
			else
			{
				entrymodulerepository.updateStatus("Submitted", Id);
			}
			
			Entry_module entry_module = entrymodulerepository.getById(Id);
			System.out.println("entry_module :"+ entry_module.toString());
						
			res = ResponseEntity.status(200).body("Record Submitted Successfully");
			ret = String.valueOf(res.getStatusCodeValue())+"|"+res.getBody();
		}
		catch(Exception e)
		{
			res = ResponseEntity.status(404).body("Record Submitted Unsuccessfully");
			ret = String.valueOf(res.getStatusCodeValue())+"|"+res.getBody();
			e.printStackTrace();
		}
		return ret;
	}
	
	@SuppressWarnings("deprecation")
	@PutMapping("/withdrawalRequest/{Id}")
	public String withdrawalRequest(@PathVariable("Id") Long id)
	{
		String ret = null;
		try
		{
			String name = JwtAuthentication.checkname();
			Users user = userrepository.findByEmailID(name);
			
			Entry_module entrymodule = entrymodulerepository.getById(id); 
//			System.out.println("entrymodule : "+entrymodule);
			
			Entry_module em = new Entry_module();
			
			em.setCdt(entrymodule.getCdt());
			em.setDate(entrymodule.getDate());
			em.setDesignation_name(entrymodule.getDesignation_name());
			em.setDivision_name(entrymodule.getDivision_name());
			em.setEmail_id(user.getEmail_id());
			em.setId(id);
			em.setName(entrymodule.getName());
			em.setMobile(entrymodule.getMobile());
			em.setExtension(entrymodule.getExtension());
			em.setPurpose(entrymodule.getPurpose());
			em.setFrom_destination(entrymodule.getFrom_destination());
			em.setTo_destination(entrymodule.getTo_destination());
			em.setSession_id(entrymodule.getSession_id());
			em.setTill_time(entrymodule.getTill_time());
			em.setTo_time(entrymodule.getTo_time());
			em.setUserid(user.getId());
			em.setStatus("withdrawal");
				
			Sanctioning_authority sanctioningauthority = sanctioningauthorityrepository.findByEid(id);
			Sanctioning_authority sa = new Sanctioning_authority();
			sa.setCdt(sanctioningauthority.getCdt());
			sa.setEid(sanctioningauthority.getEid());
			sa.setId(sanctioningauthority.getId());
			sa.setRemarks(sanctioningauthority.getRemarks());
			sa.setSession_id(sanctioningauthority.getSession_id());
			sa.setStatus("withdrawal");
			
			sanctioningauthorityrepository.save(sa);
			
			entrymodulerepository.save(em);
			ret="200";
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return ret;
	}
	
	@PostMapping("/filterUserData")
	public BookingListResponse filterData(@RequestBody FilterDataRequest filterrequest)
	{
		BookingListResponse ret = new BookingListResponse();
		try
		{
			String name = JwtAuthentication.checkname();
			Users user = userrepository.findByEmailID(name);
			
			System.out.println("----inside filter data----");
//			System.out.println("filterrequest : "+filterrequest);
			System.out.println(filterrequest.getFrom_date());
			System.out.println(filterrequest.getTo_date());
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
				
				List<Entry_module> entry_module = entrymodulerepository.filterUserData(user.getId(), fromDate, toDate);
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
					if("Allocated".equalsIgnoreCase(e.getStatus()))
					{
						allocated_booking += 1L;
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
				List<Entry_module> entry_module = entrymodulerepository.findByUser_id(user.getId());
				Long tot_booking = (long) entry_module.size();
				List<Entry_module> pending_booking = entrymodulerepository.countByUserid("Submitted", user.getId());
				List<Entry_module> em = entrymodulerepository.findApproved(user.getId(), "Approved", "Allocated", "Not allocated");
				Long approved_booking = (long) em.size();
				List<Entry_module> allocated_booking = entrymodulerepository.countByUserid("Allocated", user.getId());
				ret.setTot_booking(tot_booking);
				ret.setPending_booking((long) pending_booking.size());
				ret.setApproved_booking(approved_booking);
				ret.setAllocated_booking((long) allocated_booking.size());
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
