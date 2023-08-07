package com.csir.vehiclerequisition.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.csir.vehiclerequisition.entities.Session;

public interface SessionRepository extends JpaRepository<Session, Long>{

}
