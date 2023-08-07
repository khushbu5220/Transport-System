package com.csir.vehiclerequisition.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.csir.vehiclerequisition.entities.Session;
import com.csir.vehiclerequisition.entities.Users;
import com.csir.vehiclerequisition.helper.JwtUtil;
import com.csir.vehiclerequisition.repositories.SessionRepository;
import com.csir.vehiclerequisition.repositories.UsersRepository;
import com.csir.vehiclerequisition.request.JwtRequest;
import com.csir.vehiclerequisition.services.CustomUserDetailsService;


@CrossOrigin(origins = "*")
@RestController
public class JwtController 
{
	@Autowired
	private AuthenticationManager authenticationmanager;
	@Autowired
	private CustomUserDetailsService customuserdetailsservice;
	@Autowired
	private UsersRepository usersRepository;	
	@Autowired
	private JwtUtil jwtutil;
	@Autowired
	private SessionRepository sessionRepository;
//	@Autowired
//	private BCryptPasswordEncoder bcryptpasswordencoder;
	
	public static Long session_id = null;
	
	@RequestMapping(value = "/token",method = RequestMethod.POST)
	public String generateToken1(@RequestBody JwtRequest jwtRequest, HttpServletRequest request, HttpServletResponse response) throws Exception
	{	
		System.out.println(jwtRequest.toString());
		try {
			this.authenticationmanager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getEmail_id(), jwtRequest.getPassword()));				
		} 
		catch (UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("Bad Credentials");
		}
		catch (BadCredentialsException e) {
			e.printStackTrace();
			throw new Exception("Bad Credentials");
		}
		
		//fine area
		UserDetails userDetails = this.customuserdetailsservice.loadUserByUsername(jwtRequest.getEmail_id());
//		System.out.println("userDetails : "+userDetails.toString());
		String token = this.jwtutil.generateToken(userDetails);
//		System.out.println("JWT : " + token);
		
		//{"token":"value"}
		ResponseEntity<?> res = ResponseEntity.status(200).body(token);
		Users user = usersRepository.findByEmailID(jwtRequest.getEmail_id());
		Session sess = new Session();
		sess.setUserId(user.getId());
		sess.setIpAddress(request.getRemoteAddr());
	    sess.setLoginDt(new Date());
	    sess.setWebBrowser(request.getHeader("user-agent"));
	    Session session = sessionRepository.save(sess);
	    session_id = session.getSessionId();
//		return ResponseEntity.ok(new JwtResponse(token));
		return (String) res.getBody();
	}

}
