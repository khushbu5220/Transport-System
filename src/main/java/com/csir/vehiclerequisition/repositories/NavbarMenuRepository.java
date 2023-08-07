package com.csir.vehiclerequisition.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.csir.vehiclerequisition.entities.NavbarMenu;

public interface NavbarMenuRepository extends JpaRepository<NavbarMenu, Long>
{
	@Query("Select n from NavbarMenu n where n.foruserrole= :userrole or n.foruserrole is null and n.status= 'active' order by n.id asc")
	List<NavbarMenu> findByUserRole(@Param("userrole") String userrole);
}
