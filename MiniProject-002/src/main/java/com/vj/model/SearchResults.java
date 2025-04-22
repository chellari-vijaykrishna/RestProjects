package com.vj.model;

import java.time.LocalDate;

public class SearchResults {
	private Integer courseId;
	private String courseName;
	private String location;
	private String courseCatagory;
	private String facultyName;
	private Double courseFee;
	private Long adminContact;
	private String trainingMode;
	private LocalDate startDate;
	private String courseStatus;
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
}
