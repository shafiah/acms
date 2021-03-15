package com.management.asset.bean;



import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="Asset")
public class Asset {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer assetId;
	private String assetName;
	private Date purchaseDate;
	private String conditionNotes;
	private Integer categoryId;
	private String assignmentStatus;
	@ManyToOne
	@JoinColumn(name="employee_id")
	private EmployeeMaster employeeMaster;
	public Integer getAssetId() {
		return assetId;
	}
	public void setAssetId(Integer assetId) {
		this.assetId = assetId;
	}
	public String getAssetName() {
		return assetName;
	}
	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}
	public Date getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public String getConditionNotes() {
		return conditionNotes;
	}
	public void setConditionNotes(String conditionNotes) {
		this.conditionNotes = conditionNotes;
	}
	public String getAssignmentStatus() {
		return assignmentStatus;
	}
	public void setAssignmentStatus(String assignmentStatus) {
		this.assignmentStatus = assignmentStatus;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public EmployeeMaster getEmployeeMaster() {
		return employeeMaster;
	}
	public void setEmployeeMaster(EmployeeMaster employeeMaster) {
		this.employeeMaster = employeeMaster;
	}
}
