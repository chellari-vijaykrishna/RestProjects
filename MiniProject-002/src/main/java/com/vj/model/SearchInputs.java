package com.vj.model;

import java.time.LocalDate;


public class SearchInputs {

	private String courseCatagory;
	private String trainigMode;
	private String facultyNames;
	private LocalDate startDate;
	public String getCourseCatagory() {
		return courseCatagory;
	}
	public String getTrainigMode() {
		return trainigMode;
	}
	public String getFacultyNames() {
		return facultyNames;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setCourseCatagory(String courseCatagory) {
		this.courseCatagory = courseCatagory;
	}
	public void setTrainigMode(String trainigMode) {
		this.trainigMode = trainigMode;
	}
	public void setFacultyNames(String facultyNames) {
		this.facultyNames = facultyNames;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
}
