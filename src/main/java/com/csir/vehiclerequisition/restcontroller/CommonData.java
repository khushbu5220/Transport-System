package com.csir.vehiclerequisition.restcontroller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.csir.vehiclerequisition.config.JwtAuthenticationFilter;
import com.csir.vehiclerequisition.controller.JwtController;
import com.csir.vehiclerequisition.entities.Designations;
import com.csir.vehiclerequisition.entities.Divisions;
import com.csir.vehiclerequisition.entities.NavbarMenu;
import com.csir.vehiclerequisition.entities.Users;
import com.csir.vehiclerequisition.repositories.DesignationsRepository;
import com.csir.vehiclerequisition.repositories.DivisionsRepository;
import com.csir.vehiclerequisition.repositories.Entry_moduleRepository;
import com.csir.vehiclerequisition.repositories.Multiple_entryRepository;
import com.csir.vehiclerequisition.repositories.NavbarMenuRepository;
import com.csir.vehiclerequisition.repositories.Sanctioning_authorityRepository;
import com.csir.vehiclerequisition.repositories.Transport_cellRepository;
import com.csir.vehiclerequisition.repositories.UsersRepository;
import com.csir.vehiclerequisition.request.ChangePassword;
import com.csir.vehiclerequisition.request.JwtRequest;
import com.csir.vehiclerequisition.response.CommonDataResponse;
import com.csir.vehiclerequisition.response.NavbarMenuResponse;
import com.csir.vehiclerequisition.services.CustomUserDetailsService;

@CrossOrigin
@RestController
public class CommonData 
{
	@Autowired
	JwtAuthenticationFilter JwtAuthentication;
	@Autowired
	JwtController jwtController;
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
	@Autowired
	NavbarMenuRepository navbarmenurepository;
	
	@GetMapping("/getCommonDetails")
	public CommonDataResponse getUserDetails()
	{
		CommonDataResponse ret = new CommonDataResponse();
		try
		{
			String name = JwtAuthentication.checkname();
			Users users = userrepository.findByEmailID(name);
			
			List<Designations> designations = designationsrepository.findAll();
			List<Divisions> divisions = divisionsrepository.findAll();
			ret.setUsers(users);
			ret.setDesignations(designations);
			ret.setDivisions(divisions);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return ret;
	}
	
	@GetMapping("/getRole")
	public String getRole()
	{
		try
		{
			String name = JwtAuthentication.checkname();
			Users users = userrepository.findByEmailID(name);
			
			if(users.getRole().equalsIgnoreCase("ROLE_HOD"))
			{
				if(customuserdetailsservice.isHOD(users.getId()))
				{
					return "ROLE_HOD";
				}
				else
				{
					return "ROLE_USER";
				}
			}
			else
			{
				return users.getRole();
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "";
	}
	
	@GetMapping("/getNavbarMenu")
	public NavbarMenuResponse getNavbarMenu()
	{
		NavbarMenuResponse ret = new NavbarMenuResponse();
		try
		{
			String name = JwtAuthentication.checkname();
			Users users = userrepository.findByEmailID(name);
			
			List<NavbarMenu> menu = navbarmenurepository.findByUserRole(users.getRole());
			String username = users.getDisplay_name();
			ret.setNavbarmenu(menu);
			ret.setUsername(username);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return ret;
	}
	
	@PutMapping("/changePassword")
	public String changePassword(@RequestBody ChangePassword changepassword, HttpServletRequest request, HttpServletResponse response)
	{
		String ret = "";
		try
		{
			String name = JwtAuthentication.checkname();
			Users user = userrepository.findByEmailID(name);
			
			JwtRequest jwtr = new JwtRequest();
			jwtr.setEmail_id(user.getEmail_id());
			jwtr.setPassword(changepassword.getCurrentPassword());
			ret = "500";
			String token = jwtController.generateToken1(jwtr, request, response);
			System.out.println(token);
			Users u = new Users();
			u.setCdt(user.getCdt());
			u.setDisplay_name(user.getDisplay_name());
			u.setEmail_id(user.getEmail_id());
			u.setPassword(changepassword.getNewPassword());
			u.setRole(user.getRole());
			u.setId(user.getId());
			u.setStatus(user.getStatus());
			userrepository.save(u);
			ret = "200";
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return ret;
	}
}
