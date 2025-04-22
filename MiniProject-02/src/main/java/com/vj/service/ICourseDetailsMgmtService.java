package com.vj.service;

import java.util.List;
import java.util.Set;

import com.vj.model.SearchInputs;
import com.vj.model.SearchResults;

import jakarta.servlet.http.HttpServletResponse;

public interface ICourseDetailsMgmtService {
	
	public Set<String> showAllCourseCatagories();
	public Set<String> showAllTrainingModes();
	public Set<String> showAllFaculties();
	
	public List<SearchResults> showAllFiltersResults(SearchInputs inputs);
	
	public void getPdfreport(SearchInputs inputs,HttpServletResponse response) throws Exception;
	public void getExcelreport(SearchInputs inputs,HttpServletResponse response);

}
