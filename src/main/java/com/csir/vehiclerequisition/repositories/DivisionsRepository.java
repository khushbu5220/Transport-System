  package com.csir.vehiclerequisition.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.csir.vehiclerequisition.entities.Divisions;

public interface DivisionsRepository extends JpaRepository<Divisions, Long> 
{
	@Query("Select count(d) from Divisions d where d.user_id= :userid")
	Long count(@Param("userid") Long userid);
	
	@Query("Select d.division_name from Divisions d where d.user_id = :userid")
	String findDivision(@Param("userid") Long userid);
	
	@Query("Select d from Divisions d where d.status= 'active' ORDER BY d.id ASC")
	List<Divisions> findAllActive();
}
