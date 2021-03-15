package com.management.asset.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.management.asset.bean.Asset;
import com.management.asset.repository.AssetRepository;
import com.management.asset.repository.CategoryRepository;
import com.management.asset.service.impl.AssetServiceImpl;
import com.management.asset.util.Constant;

@SpringBootTest
public class AssetServiceTest {

	@Mock
	AssetRepository assetRepository;
	@Mock
	CategoryRepository categoryRepository;
	@InjectMocks
	private AssetService assetService = new AssetServiceImpl();

	@Test
	void contextLoads() {
	}

	@DisplayName("Test Mock SaveAsset When AssetName isEmpty")
	@Test
	void saveAssetWhenAssetNameIsEmpty() {
		Asset asset = new Asset();
		assertEquals(Constant.ASSET_NAME_MISSING, assetService.saveAsset(asset));
	}

	@DisplayName("Test Mock SaveAsset When AssetName is Duplicate")
	@Test
	void saveAssetWhenAssetNameIsduplicate() {
		Asset asset = new Asset();
		asset.setAssetName("Mouse");
		asset.setAssignmentStatus("Available");
		Mockito.when(assetRepository.findByAssetNameIgnoreCase(asset.getAssetName())).thenReturn(new Asset());
		assertEquals(Constant.DUPLICATE_ASSET, assetService.saveAsset(asset));
	}

	
	@DisplayName("Test Mock List Asset")
	@Test
	void listAssetWhenAssetNameIsPresent() {
		Asset asset = new Asset();
		asset.setAssetName("Mouse");
		Mockito.when(assetRepository.findByAssetNameIgnoreCase(asset.getAssetName())).thenReturn(new Asset());
		assertNotNull(assetService.findAllAssetDetail("Mouse"));
	}

	@DisplayName("Test Mock List Asset")
	@Test
	void listAsset() {
		Mockito.when(assetRepository.findAll()).thenReturn(new ArrayList<Asset>());
		assertNotNull(assetService.findAllAssetDetail(null));
	}

	@DisplayName("Test Mock updateAsset When AssetId Is Null")
	@Test
	void updateAssetWhenAssetIdNull() {
		Asset asset = new Asset();
		assertEquals(Constant.ID_MISSING, assetService.updateAsset(asset));
	}

	@DisplayName("Test Mock updateAsset When AssetName isEmpty")
	@Test
	void updateAssetWhenAssetNameIsEmpty() {
		Asset asset = new Asset();
		asset.setAssetId(1);
		assertEquals(Constant.ASSET_NAME_MISSING, assetService.updateAsset(asset));
	}

	@DisplayName("Mock test updateAsset Invalid category")
	@Test
	void updateAssetInValidcategory() {
		Asset asset = new Asset();
		asset.setAssetId(1);;
		asset.setCategoryId(1);
		asset.setAssetName("Test");
		asset.setAssignmentStatus("Available");
		assertEquals(Constant.INVALID_CATEGORY, assetService.updateAsset(asset));
	}

}
