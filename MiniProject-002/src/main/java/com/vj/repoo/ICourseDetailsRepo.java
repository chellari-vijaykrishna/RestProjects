package com.vj.repoo;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vj.enity.CourseDetails;

public interface ICourseDetailsRepo extends JpaRepository<CourseDetails, Integer> {
	
	@Query("select distinct(courseCatagory) from CourseDetails")
	public Set<String> getAllUniqueCatagories();
	
	@Query("select distinct(trainingMode) from CourseDetails")
	public Set<String> getAllUniqueTrainingModes();
	
	@Query("select distinct(facultyName) from CourseDetails")
	public Set<String> getAllUniqueFacultyNames();

}
