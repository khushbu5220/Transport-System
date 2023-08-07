package com.csir.vehiclerequisition.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.csir.vehiclerequisition.entities.Transport_cell;

public interface Transport_cellRepository extends JpaRepository<Transport_cell, Long>
{
	@Query("Select t from Transport_cell t where t.eid= :eid")
	Transport_cell findByEid(@Param("eid") Long eid);
}
