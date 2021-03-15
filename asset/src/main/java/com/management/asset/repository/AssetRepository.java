package com.management.asset.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.management.asset.bean.Asset;


public interface AssetRepository extends JpaRepository<Asset, Integer> {

	public List<Asset> findByAssetNameContainingIgnoreCase(String assetName);
	
	public Asset findByAssetNameIgnoreCase(String assetName);
	
	@Modifying
    @Transactional
	@Query(value="Insert into employee_asset(employee_id,asset_id) Values(?1,?2)",nativeQuery =true)
	public void insert(Integer employeeId,Integer assetId);
	
	
	
}
