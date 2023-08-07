package com.csir.vehiclerequisition.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.csir.vehiclerequisition.entities.Users;
import com.csir.vehiclerequisition.helper.CustomUserDetails;
import com.csir.vehiclerequisition.repositories.DivisionsRepository;
import com.csir.vehiclerequisition.repositories.UsersRepository;


@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UsersRepository userRepository;
	@Autowired
	private DivisionsRepository divisionsrepository;
	
//	@SuppressWarnings("unused")
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		System.out.println(userName +" : userName");
		//user from database
//				String username = userRepository.getname(userName);
				Users user = this.userRepository.findByEmailID(userName);
//				System.out.println(user.toString() +" : user");
				if(user == null) 
				{
					throw new UsernameNotFoundException("User not found !!");
				}
				else 
				{
					return new CustomUserDetails(user);
				}
		
		
//		if(userName.equals("Ashish")){
//			return new User("Ashish","Ashish123",new ArrayList<>());
//		}
//		else {
//			throw new UsernameNotFoundException("User not found");
//		}
	}

	public boolean isHOD(Long userid)
	{
		boolean ret = false;
		try
		{
			Long count = divisionsrepository.count(userid);
			if(count != 0)
				ret = true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return ret;
	}
	
}
