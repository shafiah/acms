package com.management.asset.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.management.asset.bean.Asset;


@Service
public interface AssetService {

	public String saveAsset(Asset asset);
	public String updateAsset(Asset asset);
	public List<Asset> findAllAssetDetail(String assetName);
	public String assignAsset(Integer assetId,Integer employeeId);
	public String deAssignAsset(Integer assetId,Integer employeeId);
	public String deleteAsset(Integer assetId);
}
