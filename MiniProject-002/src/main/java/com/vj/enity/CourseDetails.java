package com.vj.enity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "COURSE_DETAILS")
public class CourseDetails {

	@Id
	@GeneratedValue
	@Column(name = "COURSE_ID")
	private Integer courseId;
	
	@Column(length = 20,name = "COURSE_NAME")
	private String courseName;
	
	@Column(length = 20,name = "LOCATION")
	private String location;
	
	@Column(length = 20,name = "COURSE_CATAGORY")
	private String courseCatagory;
	
	@Column(length = 20,name = "FACULTY_NAME")
	private String facultyName;
	
	@Column(name = "COURSE_FEE")
	private Double courseFee;
	
	@Column(length = 20,name = "ADMIN_NAME")
	private String adminName;
	
	@Column(name = "ADMIN_CONTACT")
	private Long adminContact;
	
	@Column(length = 20,name = "TRAINING_MODE")
	private String trainingMode;
	
	@Column(length = 20,name = "START_DATE")
	private LocalDate startDate;
	
	@Column(length = 20,name = "COURSE_STATUS")
	private String courseStatus;
	
	
	@Column(name = "CREATED_BY",insertable = true,updatable = false)
	private LocalDateTime createdBy;
	@Column(name = "UPDATED_BY",insertable = false,updatable = true)
	private LocalDateTime updatedBy;
	
	@CreationTimestamp
	@Column(name = "UPDATED_DATE")
	private LocalDateTime createdDate;
	@UpdateTimestamp
	@Column(name = "CREATED_DATE")
	private LocalDateTime updatedDate;
	
	

	
	
	public Integer getCourseId() {
		return courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public String getLocation() {
		return location;
	}
	public String getCourseCatagory() {
		return courseCatagory;
	}
	public String getFacultyName() {
		return facultyName;
	}
	public Double getCourseFee() {
		return courseFee;
	}
	public String getAdminName() {
		return adminName;
	}
	public Long getAdminContact() {
		return adminContact;
	}
	public String getTrainingMode() {
		return trainingMode;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public String getCourseStatus() {
		return courseStatus;
	}
	public LocalDateTime getCreatedBy() {
		return createdBy;
	}
	public LocalDateTime getUpdatedBy() {
		return updatedBy;
	}
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}
	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public void setCourseCatagory(String courseCatagory) {
		this.courseCatagory = courseCatagory;
	}
	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}
	public void setCourseFee(Double courseFee) {
		this.courseFee = courseFee;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public void setAdminContact(Long adminContact) {
		this.adminContact = adminContact;
	}
	public void setTrainingMode(String trainingMode) {
		this.trainingMode = trainingMode;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public void setCourseStatus(String courseStatus) {
		this.courseStatus = courseStatus;
	}
	public void setCreatedBy(LocalDateTime createdBy) {
		this.createdBy = createdBy;
	}
	public void setUpdatedBy(LocalDateTime updatedBy) {
		this.updatedBy = updatedBy;
	}
	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}
	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}
	

}
