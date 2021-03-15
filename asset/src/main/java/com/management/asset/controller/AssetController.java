package com.management.asset.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.management.asset.bean.Asset;
import com.management.asset.pojo.AssetEmployeePojo;
import com.management.asset.service.AssetService;

@RestController
@RequestMapping("/asset")
public class AssetController {
	
	@Autowired
	AssetService assetService;
	
	@PostMapping("/add")
	public ResponseEntity<String> createAsset(@RequestBody Asset asset) {
		return new ResponseEntity<>(assetService.saveAsset(asset),HttpStatus.OK);
	}
	@PutMapping("/update")
	public ResponseEntity<String> updateAsset(@RequestBody Asset asset) {
		return new ResponseEntity<>(assetService.updateAsset(asset),HttpStatus.OK);
	}
	
	@PutMapping("/assign")
	public ResponseEntity<String> assignEmployeeAsset(@RequestBody AssetEmployeePojo assetEmployeePojo) {
		return new ResponseEntity<>(assetService.assignAsset(assetEmployeePojo.getAssetId(), assetEmployeePojo.getEmployeeId()),HttpStatus.OK);
		
	}
	
	@PutMapping("/de-assign")
	public ResponseEntity<String> deAssignEmployeeAsset(@RequestBody AssetEmployeePojo assetEmployeePojo) {
		return new ResponseEntity<>(assetService.deAssignAsset(assetEmployeePojo.getAssetId(), assetEmployeePojo.getEmployeeId()),HttpStatus.OK);
		
	}
	
	@GetMapping("/list")
	public ResponseEntity<Object> listAssetGet(@RequestParam(name="assetName",required=false) String assetName){
		
		 return new ResponseEntity<>(assetService.findAllAssetDetail(assetName),HttpStatus.OK);
		
	}
	
	@DeleteMapping("/{assetId}")
	public ResponseEntity<String> deleteAsset(@PathVariable int assetId){
		return new ResponseEntity<>(assetService.deleteAsset(assetId),HttpStatus.OK);
	}
	
}
