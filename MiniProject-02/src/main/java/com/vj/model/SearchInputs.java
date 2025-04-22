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
	
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setCourseCatagory(String courseCatagory) {
		this.courseCatagory = courseCatagory;
	}
	public void setTrainigMode(String trainigMode) {
		this.trainigMode = trainigMode;
	}
	
	public SearchInputs(String courseCatagory, String trainigMode, String facultyNames, LocalDate startDate) {
		super();
		this.courseCatagory = courseCatagory;
		this.trainigMode = trainigMode;
		this.facultyNames = facultyNames;
		this.startDate = startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	
	public SearchInputs() {
		
	}
	public String getFacultyNames() {
		return facultyNames;
	}
	public void setFacultyNames(String facultyNames) {
		this.facultyNames = facultyNames;
	}
	@Override
	public String toString() {
		return "SearchInputs [courseCatagory=" + courseCatagory + ", trainigMode=" + trainigMode + ", facultyNames="
				+ facultyNames + ", startDate=" + startDate + "]";
	} 

}
