package com.csir.vehiclerequisition.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.csir.vehiclerequisition.entities.Multiple_entry;

public interface Multiple_entryRepository extends JpaRepository<Multiple_entry, Long>
{
	@Transactional
	@Modifying
	@Query("Delete from Multiple_entry m where m.eid= :eid")
	int deleteByEid(@Param("eid") Long eid);
	
	@Query("Select m from Multiple_entry m where m.eid= :eid and m.status= 'active'")
	List<Multiple_entry> findByEid(@Param("eid") Long eid);
}
