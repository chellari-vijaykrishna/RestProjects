package com.vj.repo;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vj.entity.CourseDetalis;

@Repository
public interface ICourseDetailsRepo extends JpaRepository<CourseDetalis, Integer> {
	
	@Query("select distinct(courseCatagory) from CourseDetails")
	public Set<String> getUniqueCourseCatagories();
	
	@Query("select distinct(facultyName) from CourseDetails")
	public Set<String> getUniqueFacultyNames();
	
	@Query("select distinct(trainingMode) from CourseDetails")
	public Set<String> getUniqueTrainingMode();

}
