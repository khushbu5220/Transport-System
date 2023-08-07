package com.csir.vehiclerequisition.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.csir.vehiclerequisition.entities.Users;

public interface UsersRepository extends JpaRepository<Users, Long>
{
	@Query("Select u from Users u where u.email_id = :email_id and u.status = 'active'")
	Users findByEmailID(@Param("email_id") String email_id);
	
	@Query("Select u from Users u where u.id = :id  ORDER BY u.cdt DESC")
	Users findByUserId(@Param("id") Long id);
	
	@Query("Select u.display_name from Users u where u.email_id= :email_id and u.status= 'active'")
	String getname(@Param("email_id") String email_id);
}
