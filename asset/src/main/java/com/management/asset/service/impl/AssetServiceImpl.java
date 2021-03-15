package com.management.asset.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.management.asset.bean.Asset;
import com.management.asset.bean.Category;
import com.management.asset.bean.EmployeeMaster;
import com.management.asset.repository.AssetRepository;
import com.management.asset.repository.CategoryRepository;
import com.management.asset.repository.EmployeeMasterRepository;
import com.management.asset.service.AssetService;
import com.management.asset.util.AssignmentStatus;
import com.management.asset.util.Constant;

@Service
public class AssetServiceImpl implements AssetService {

	@Autowired
	private AssetRepository assetRepository;
	
	@Autowired private CategoryRepository catetoryRepository;
	
	@Autowired
	EmployeeMasterRepository employeeMasterRepository;
	
	private Asset save(Asset asset) {
		return assetRepository.save(asset);
	}
	
	@Override
	public String saveAsset(Asset asset) {
		String result=null;
		if (!StringUtils.hasText(asset.getAssetName())) {
			result = Constant.ASSET_NAME_MISSING;
		}else if(!contains(asset.getAssignmentStatus())){
			result=Constant.INVALID_ASSIGNMENT_STATUS;
		}else {
			if (assetRepository.findByAssetNameIgnoreCase(asset.getAssetName()) != null) {
				result = Constant.DUPLICATE_ASSET;
			}else if(!checkCategoryIdExistOrNot(asset.getCategoryId())) {
				result=Constant.INVALID_CATEGORY;
			} else {
				asset.setAssetId(null);
				asset = save(asset);
				result = Constant.ADDED_SUCCESSFULLY;
			}
		}
		return result;
	}
	
	private boolean contains(String status) {
	    for (AssignmentStatus c : AssignmentStatus.values()) {
	        if (c.name().equals(status)) {
	            return true;
	        }
	    }
	    return false;
	}
	
	
	/**
	 * 
	 * @param categoryId 
	 * @return true if categoryId is present in storage else false;
	 */
	private boolean checkCategoryIdExistOrNot(Integer categoryId) {
		boolean flag=false;
		Category category=catetoryRepository.findById(categoryId).orElse(null);
		if(category!=null) {
			flag=true;
		}
		return flag;
	}
	
	/** 
	 * @param assetName
	 * @return List of asset based on input parameter 
	 */
	@Override
	public List<Asset> findAllAssetDetail(String assetName) {
			if(StringUtils.hasText(assetName)) {
				return assetRepository.findByAssetNameContainingIgnoreCase(assetName);
			}else {
				return assetRepository.findAll();
			}
	}
	
	
	public void assigned(String employeeName,String assetName) {
		try {
			Asset asset=assetRepository.findByAssetNameIgnoreCase(assetName);
			EmployeeMaster employeeMaster=employeeMasterRepository.findByEmployeeName(employeeName);
			assetRepository.insert(employeeMaster.getEmployeeId(), asset.getAssetId());
		}			
		catch(Exception e1) {
			e1.printStackTrace();
		}
	}

	

	@Override
	public String updateAsset(Asset asset) {
		String result = null;
		if (asset.getAssetId() == null || asset.getAssetId()==0) {
			result = Constant.ID_MISSING;
		} else if (!StringUtils.hasText(asset.getAssetName())) {
			result = Constant.ASSET_NAME_MISSING;
		} else if(!contains(asset.getAssignmentStatus())){
			result=Constant.INVALID_ASSIGNMENT_STATUS;
		}else if(!checkCategoryIdExistOrNot(asset.getCategoryId())) {
			result=Constant.INVALID_CATEGORY;
		} else {
			Asset temp = assetRepository.findByAssetNameIgnoreCase(asset.getAssetName());
			if (temp == null || temp.getAssetId() == asset.getAssetId()) {
				save(asset);
				result = Constant.ADDED_SUCCESSFULLY;
			} else {
				result = Constant.DUPLICATE_ASSET;
			}
		}
		return result;
	}

	@Override
	public String assignAsset(Integer assetId, Integer employeeId) {
		String result=null;
		Asset asset=assetRepository.findById(assetId).orElse(null);
		if(asset==null) {
			result=Constant.INVALID_ASSET_ID;			
		}else {
			EmployeeMaster employee=employeeMasterRepository.findById(employeeId).orElse(null);
			if(employee==null) {
				result=Constant.INVALID_EMPLOYEE_ID;
			}else{
				asset.setEmployeeMaster(employee);
				save(asset);
				result=Constant.ADDED_SUCCESSFULLY;
			}
		}
		return result;
	}

	
	/**
	 * @param assetId, employeeId
	 * recover and asset from employee
	 * @return message on the base of action performed
	 */
	@Override
	public String deAssignAsset(Integer assetId, Integer employeeId) {
		String result=null;
		Asset asset=assetRepository.findById(assetId).orElse(null);
		if(asset==null) {
			result=Constant.INVALID_ASSET_ID;			
		}else {
			EmployeeMaster employee=employeeMasterRepository.findById(employeeId).orElse(null);
			if(employee==null) {
				result=Constant.INVALID_EMPLOYEE_ID;
			}else{
				asset.setEmployeeMaster(null);
				save(asset);
				result=Constant.ADDED_SUCCESSFULLY;
			}
		}
		return result;
	}

	@Override
	public String deleteAsset(Integer assetId) {
		String result=null;
		Asset asset=assetRepository.findById(assetId).orElse(null);
		if(asset==null) {
			result=Constant.INVALID_ASSET_ID;	
		}else if(asset.getEmployeeMaster()!=null) {
			result=Constant.ATTACHED_ASSET;
		}else {
			assetRepository.delete(asset);
			result=Constant.ASSET_DELETED;
		}
		return result;
	}
}
