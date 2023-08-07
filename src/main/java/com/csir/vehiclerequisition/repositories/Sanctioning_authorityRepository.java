package com.csir.vehiclerequisition.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.csir.vehiclerequisition.entities.Sanctioning_authority;

public interface Sanctioning_authorityRepository extends JpaRepository<Sanctioning_authority, Long>
{
	@Query("Select s from Sanctioning_authority s where s.eid= :eid")
	Sanctioning_authority findByEid(Long eid);
}
