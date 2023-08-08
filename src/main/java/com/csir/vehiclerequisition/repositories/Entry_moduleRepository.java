package com.csir.vehiclerequisition.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.csir.vehiclerequisition.entities.Entry_module;

public interface Entry_moduleRepository extends JpaRepository<Entry_module, Long> 
{
	@Query("Select e from Entry_module e where e.userid= :userid ORDER BY e.cdt DESC")
	List<Entry_module> findByUser_id(@Param("userid") Long userid);
	
	@Transactional
	@Modifying
	@Query("Update Entry_module e set e.status= :status where e.id= :Id")
	int updateStatus(@Param("status") String status, @Param("Id") Long Id);
	
	@Query("Select e from Entry_module e where e.status is not :status and e.division_name= :division ORDER BY e.cdt DESC")
	List<Entry_module> findByStatus(@Param("status") String status, @Param("division") String division);

	@Query("Select e from Entry_module e where e.division_name= :division_name and (e.status= :status1 or e.status= :status2 or e.status= :status3) ORDER BY e.cdt DESC")
	List<Entry_module> findByStatus(@Param("status1") String status1, @Param("status2") String status2, @Param("status3") String status3, @Param("division_name") String division_name);
	
	@Query("Select e from Entry_module e where e.status= :status1 or e.status= :status2 or e.status= :status3 ORDER BY e.cdt DESC")
	List<Entry_module> findByStatus(@Param("status1") String status1, @Param("status2") String status2, @Param("status3") String status3);
	
	@Query("Select e from Entry_module e where e.userid= :userid and (e.status= :status1 or e.status= :status2 or e.status= :status3) ORDER BY e.cdt DESC")
	List<Entry_module> findApproved(@Param("userid") Long userid, @Param("status1") String status1, @Param("status2") String status2, @Param("status3") String status3);
	
	@Query("Select e from Entry_module e where e.status= :status ORDER BY e.cdt DESC")
	List<Entry_module> countlist(@Param("status") String status);
	
	@Query("Select e from Entry_module e where e.status= :status and e.userid= :userid ORDER BY e.cdt DESC")
	List<Entry_module> countByUserid(@Param("status") String status, @Param("userid") Long userid);
	
	@Query("Select e from Entry_module e where e.status= :status and e.division_name= :division")
	List<Entry_module> countByDiv(@Param("status") String status, @Param("division") String division);
	
	@Query("Select e from Entry_module e where e.division_name= :division_name and (e.cdt >= :from_date and e.cdt <= :to_date ) and (e.status= :status1 or e.status= :status2 or e.status= :status3 ) ORDER BY e.cdt DESC")
	List<Entry_module> filterData(@Param("status1") String status1,@Param("status2") String status2,@Param("status3") String status3, @Param("division_name") String division_name, @Param("from_date") Date from_date, @Param("to_date") Date to_date);

	@Query("Select e from Entry_module e where e.userid= :userid and e.cdt>= :from_date or e.cdt<= :to_date ORDER BY e.cdt DESC")
	List<Entry_module> filterUserData(@Param("userid") Long userid, @Param("from_date") Date from_date, @Param("to_date") Date to_date);
	
	@Query("Select e from Entry_module e where e.division_name= :division_name and (e.cdt>= :from_date and e.cdt<= :to_date) ORDER BY e.cdt DESC")
	List<Entry_module> filterSanctionData(@Param("division_name") String division_name, @Param("from_date") Date from_date, @Param("to_date") Date to_date);
}
