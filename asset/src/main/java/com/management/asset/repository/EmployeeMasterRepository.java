package com.management.asset.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.management.asset.bean.EmployeeMaster;

public interface EmployeeMasterRepository extends JpaRepository<EmployeeMaster, Integer> {

	public EmployeeMaster  findByEmployeeName(String employeeName );
	
	@Modifying
    @Transactional
	@Query(value="Insert into employee_asset(employee_id,asset_id) Values(?1,?2)",nativeQuery =true)
	public void insert(@Param("employeeId")Integer employeeId,@Param("assetId")Integer assetId);
}
