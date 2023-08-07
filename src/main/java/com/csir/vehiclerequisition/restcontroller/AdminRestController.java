package com.csir.vehiclerequisition.restcontroller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.csir.vehiclerequisition.entities.Divisions;
import com.csir.vehiclerequisition.entities.Users;
import com.csir.vehiclerequisition.repositories.DesignationsRepository;
import com.csir.vehiclerequisition.repositories.DivisionsRepository;
import com.csir.vehiclerequisition.repositories.Entry_moduleRepository;
import com.csir.vehiclerequisition.repositories.Multiple_entryRepository;
import com.csir.vehiclerequisition.repositories.Sanctioning_authorityRepository;
import com.csir.vehiclerequisition.repositories.Transport_cellRepository;
import com.csir.vehiclerequisition.repositories.UsersRepository;
import com.csir.vehiclerequisition.response.DivisionListResponse;
import com.csir.vehiclerequisition.services.CustomUserDetailsService;

@CrossOrigin
@RestController
public class AdminRestController 
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
	
	@PostMapping("/SaveUser")
	public String SaveUser(@RequestBody Users userData)
	{
		String ret = "";
		ResponseEntity<?> res = null;
		try
		{
			String name = JwtAuthentication.checkname();
			Users user = userrepository.findByEmailID(name);
			
			Users u = new Users();
			u.setCdt(new Date());
			if("".equalsIgnoreCase(userData.getDisplay_name()))
			{
				res = ResponseEntity.status(400).body("Name is required");
				return String.valueOf(res.getStatusCodeValue())+"|"+res.getBody();
			}
			u.setDisplay_name(userData.getDisplay_name());
			
			if("".equalsIgnoreCase(userData.getEmail_id()))
			{
				res = ResponseEntity.status(400).body("Email ID is required");
				return String.valueOf(res.getStatusCodeValue())+"|"+res.getBody();
			}
			u.setEmail_id(userData.getEmail_id());
			
			if("".equalsIgnoreCase(userData.getPassword()))
			{
				res = ResponseEntity.status(400).body("Password is required");
				return String.valueOf(res.getStatusCodeValue())+"|"+res.getBody();
			}
			u.setPassword(userData.getPassword());
			
			if("".equalsIgnoreCase(userData.getRole()))
			{
				res = ResponseEntity.status(400).body("Role is required");
				return String.valueOf(res.getStatusCodeValue())+"|"+res.getBody();
			}
			u.setRole(userData.getRole());
			u.setSessionid("");
			u.setStatus("active");
			userrepository.save(u);
			
			res = ResponseEntity.status(201).body("Record Saved Successfully");
			ret = String.valueOf(res.getStatusCodeValue())+"|"+res.getBody();
		}
		catch(Exception e)
		{
			res = ResponseEntity.status(404).body("Record Saved Unsuccessfully");
			ret = String.valueOf(res.getStatusCodeValue())+"|"+res.getBody();
			e.printStackTrace();
		}
		return ret;
	}
	
	@GetMapping("/UsersList")
	public List<Users> UsersList()
	{
		List<Users> ret = null;
		try
		{
			ret = userrepository.findAll();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return ret;
	}
	
	@GetMapping("/UserData/{Id}")
	public Users UserData(@PathVariable("Id") Long Id)
	{
		Users ret = null;
		try
		{
			ret = userrepository.findByUserId(Id);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return ret;
	}
	
	@PutMapping("/EditUserData/{Id}")
	public String EditUserData(@PathVariable("Id") Long Id, @RequestBody Users userData)
	{
		String ret = "";
		ResponseEntity<?> res = null;
		try
		{
			String name = JwtAuthentication.checkname();
			Users user = userrepository.findByEmailID(name);
			
			Users ud = userrepository.findByUserId(Id);
			
			Users u = new Users();
			if("".equalsIgnoreCase(userData.getDisplay_name()))
			{
				res = ResponseEntity.status(400).body("Name is required");
				return String.valueOf(res.getStatusCodeValue())+"|"+res.getBody();
			}
			u.setDisplay_name(userData.getDisplay_name());
			
			if("".equalsIgnoreCase(userData.getEmail_id()))
			{
				res = ResponseEntity.status(400).body("Email ID is required");
				return String.valueOf(res.getStatusCodeValue())+"|"+res.getBody();
			}
			u.setEmail_id(userData.getEmail_id());
			
			u.setPassword(userData.getPassword());
			
			if("".equalsIgnoreCase(userData.getRole()))
			{
				res = ResponseEntity.status(400).body("Role is required");
				return String.valueOf(res.getStatusCodeValue())+"|"+res.getBody();
			}
			u.setCdt(ud.getCdt());
			u.setPassword(ud.getPassword());
			u.setId(Id);
			u.setRole(userData.getRole());
			u.setSessionid("");
			u.setStatus(userData.getStatus());
			userrepository.save(u);
			
			res = ResponseEntity.status(201).body("Record Updated Successfully");
			ret = String.valueOf(res.getStatusCodeValue())+"|"+res.getBody();
		}
		catch(Exception e)
		{
			res = ResponseEntity.status(404).body("Record Updated Unsuccessfully");
			ret = String.valueOf(res.getStatusCodeValue())+"|"+res.getBody();
			e.printStackTrace();
		}
		return ret;
	}
	
	@GetMapping("/divisionList")
	public List<DivisionListResponse> divisionList()
	{
		List<DivisionListResponse> ret = new ArrayList<>();
		try
		{
			List<Divisions> divisionList = divisionsrepository.findAllActive();
			System.out.println("divisionList: " +divisionList.toString());
			for(Divisions d : divisionList)
			{
				DivisionListResponse divisionlistresponse = new DivisionListResponse();
				divisionlistresponse.setDivisions(d);
				Users user = userrepository.findByUserId(d.getUser_id());
				divisionlistresponse.setUsername(user.getEmail_id());
				ret.add(divisionlistresponse);
			}
			System.out.println("ret : "+ret);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return ret;
	}
}
