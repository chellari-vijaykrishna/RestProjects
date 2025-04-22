package com.vj.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "USER_MASTER")
public class UserMaster {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer userId;
	@Column(name = "NAME")
	private String name;
	@Column(name = "EMAIL")
	private String email;
	@Column(name = "PASSWORD")
	private String password;
	@Column(name = "MOBILE_NO")
	private Long mobileNo;
	@Column(name = "AADHAR_NO")
	private Long aadharNo;
	@Column(name = "GENDER")
	private String gender;
	
	@Column(name = "DOB")
	private LocalDate dob;
	
	@Column(name = "ACTIVE_SW")
	private String active_sw;
	
	@CreationTimestamp
	@Column(name = "CREATED_ON",insertable = true)
	private LocalDateTime createdOn;
	@UpdateTimestamp
	@Column(name = "UPDATE_ON", updatable = true)
	private LocalDateTime updateOn;
	
	@Column(name = "CREATED_BY")
	private String createdBy;
	@Column(name = "UPDATED_BY")
	private String updatedBy;
	public Integer getUserId() {
		return userId;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public Long getMobileNo() {
		return mobileNo;
	}
	public Long getAadharNo() {
		return aadharNo;
	}
	public String getGender() {
		return gender;
	}
	public LocalDate getDob() {
		return dob;
	}
	public String getActive_sw() {
		return active_sw;
	}
	public LocalDateTime getCreatedOn() {
		return createdOn;
	}
	public LocalDateTime getUpdateOn() {
		return updateOn;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setMobileNo(Long mobileNo) {
		this.mobileNo = mobileNo;
	}
	public void setAadharNo(Long aadharNo) {
		this.aadharNo = aadharNo;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public void setActive_sw(String active_sw) {
		this.active_sw = active_sw;
	}
	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}
	public void setUpdateOn(LocalDateTime updateOn) {
		this.updateOn = updateOn;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public UserMaster() {
		
	}
	public UserMaster(Integer userId, String name, String email, String password, Long mobileNo, Long aadharNo,
			String gender, LocalDate dob, String active_sw, LocalDateTime createdOn, LocalDateTime updateOn,
			String createdBy, String updatedBy) {
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.mobileNo = mobileNo;
		this.aadharNo = aadharNo;
		this.gender = gender;
		this.dob = dob;
		this.active_sw = active_sw;
		this.createdOn = createdOn;
		this.updateOn = updateOn;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
	}
	
	
	
}
