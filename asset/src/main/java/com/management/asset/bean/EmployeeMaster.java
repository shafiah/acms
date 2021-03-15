package com.management.asset.bean;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="employee_master")
public class EmployeeMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer employeeId;
	private String employeeName;
	private String designation;
	
	@OneToMany(fetch =FetchType.LAZY)
	private List<Asset> asset;
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public List<Asset> getAsset() {
		return asset;
	}
	public void setAsset(List<Asset> asset) {
		this.asset = asset;
	}
	
	
	
}
