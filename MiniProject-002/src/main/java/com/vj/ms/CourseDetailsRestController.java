package com.vj.ms;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vj.model.SearchInputs;
import com.vj.model.SearchResults;
import com.vj.service.ICourseDetailsServiceMgmt;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/course-details")
public class CourseDetailsRestController {
	
	@Autowired
	private ICourseDetailsServiceMgmt serviceRepo;
	
	
	@GetMapping("/unq-catagories")
	public ResponseEntity<?> getAllUniCourseCatagory(){
		try {
			Set<String> res = serviceRepo.showAllCourseCatagories();
			return new ResponseEntity<Set<String>>(res,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	@GetMapping("/unq-training-modes")
	public ResponseEntity<?> getAllUniTraningModes(){
		try {
			Set<String> res = serviceRepo.showAllTrainingModes();
			return new ResponseEntity<Set<String>>(res,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	@GetMapping("/unq-faculties")
	public ResponseEntity<?> getAllUniFaculties(){
		try {
			Set<String> res = serviceRepo.showAllFaculties();
			return new ResponseEntity<Set<String>>(res,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	@GetMapping("/filter-results")
	public ResponseEntity<?> getAllFilterData(@RequestBody SearchInputs inputs){
		try {
			List<SearchResults> res = serviceRepo.showAllFiltersResults(inputs);
			return new ResponseEntity<List<SearchResults>>(res,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	@GetMapping("/pdf-report")
	public void getPdfReport(@RequestBody SearchInputs inputs,HttpServletResponse res){
		try {
			res.setContentType("application/pdf");
			res.setHeader("Content-Disposition", "attachment;fileName=courses.pdf");
			serviceRepo.getPdfreport(inputs, res);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	@GetMapping("/all-pdf-report")
	public ResponseEntity<?> getAllPdfReport(HttpServletResponse res){
		try {
			res.setContentType("application/pdf");
			res.setHeader("Content-Disposition", "attachment;fileName=courses.pdf");
			return new ResponseEntity<String>( serviceRepo.getAllPdfreport(res),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	
	@GetMapping("/excel-report")
	public void getExcelReport(@RequestBody SearchInputs inputs,HttpServletResponse res){
		try {
			res.setContentType("application/vd.ms-excel");
			res.setHeader("Content-Disposition", "attachment;fileName=courses.xls");
			serviceRepo.getExcelreport(inputs, res);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	@GetMapping("/all-excel-report")
	public ResponseEntity<?> getAllExcelReport(HttpServletResponse res){
		try {
			res.setContentType("application/vd.ms-excel");
			res.setHeader("Content-Disposition", "attachment;fileName=courses.xls");
			return new ResponseEntity<String>( serviceRepo.getAllExcelreport(res),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
}
