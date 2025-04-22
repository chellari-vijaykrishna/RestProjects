package com.vj.bindings;

import java.time.LocalDate;

public class UserAccount {
	private Integer userId;
	private String name;
	private String email;
	private Long mobileNo;
	private String gender = "Female";
	private LocalDate dob = LocalDate.now();
	private Long aadharNo;
	
	public Integer getUserId() {
		return userId;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public Long getMobileNo() {
		return mobileNo;
	}
	public String getGender() {
		return gender;
	}
	public LocalDate getDob() {
		return dob;
	}
	public Long getAadharNo() {
		return aadharNo;
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
	public void setMobileNo(Long mobileNo) {
		this.mobileNo = mobileNo;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public void setAadharNo(Long aadharNo) {
		this.aadharNo = aadharNo;
	}
	public UserAccount(Integer userId, String name, String email, Long mobileNo, String gender, LocalDate dob,
			Long aadharNo) {
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.mobileNo = mobileNo;
		this.gender = gender;
		this.dob = dob;
		this.aadharNo = aadharNo;
	}
	public UserAccount() {
		
	}
	
	
}
