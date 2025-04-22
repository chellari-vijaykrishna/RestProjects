package com.vj.service;

import java.awt.Color;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.vj.enity.CourseDetails;
import com.vj.model.SearchInputs;
import com.vj.model.SearchResults;
import com.vj.repoo.ICourseDetailsRepo;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;


@Service
public class ICourseDetailsServiceMgmtImpl implements ICourseDetailsServiceMgmt {
	
	@Autowired
	private ICourseDetailsRepo repo;

	@Override
	public Set<String> showAllCourseCatagories() {
		Set<String> res = repo.getAllUniqueCatagories(); 
		return res;
	}

	@Override
	public Set<String> showAllTrainingModes() {
		Set<String> res = repo.getAllUniqueTrainingModes(); 
		return res;
	}

	@Override
	public Set<String> showAllFaculties() {
		Set<String> res = repo.getAllUniqueFacultyNames(); 
		return res;
	}

	@Override
	public List<SearchResults> showAllFiltersResults(SearchInputs inputs) {
		
		
		CourseDetails entity = new CourseDetails();
		String catagory = inputs.getCourseCatagory();
		if(catagory!=null) {
			entity.setCourseCatagory(catagory);
		}
		String trainingModes = inputs.getTrainigMode();
		if(trainingModes!=null ) {
			entity.setTrainingMode(trainingModes);
		}
		String facultiesName  = inputs.getFacultyNames();
		if(facultiesName!=null ) {
			entity.setTrainingMode(facultiesName);
		}
		
		LocalDate startDate = inputs.getStartDate();
		if(startDate!=null) {
		    entity.setStartDate(startDate);
		}
		
		Example<CourseDetails> example  = Example.of(entity);
		
		List<CourseDetails> listCourse = repo.findAll(example);
		//Convert CourseDetails to SearchResults 
		
		
		
		List<SearchResults> listSearch = new  ArrayList<>();
		
		listCourse.forEach(itera->{
			SearchResults results = new SearchResults();
			BeanUtils.copyProperties(itera, results);
			listSearch.add(results);
		});
		
		return listSearch;
		
	/*	CourseDetails entity = new CourseDetails();
		
		String catagory = inputs.getCourseCatagory();
		if(StringUtils.hasLength(catagory));
		entity.setCourseCatagory(catagory);
		
		String trModes = inputs.getTrainigMode();
		if(StringUtils.hasLength(trModes));
		entity.setTrainingMode(trModes);
		
		String faculties = inputs.getFacultyNames();
		if(StringUtils.hasLength(faculties));
		entity.setFacultyName(faculties);
		
		LocalDate startDate = inputs.getStartDate();
		if(startDate!=null)
			entity.setStartDate(startDate);
		
		Example<CourseDetails> example = Example.of(entity);
		
		List<CourseDetails> listEntities = repo.findAll(example);
		
		List<SearchResults> results = new ArrayList<SearchResults>();
		
		listEntities.forEach(course->{
			SearchResults result = new SearchResults();
			BeanUtils.copyProperties(course, result);
			results.add(result);
		});
		return results; */
	}

	@Override
	public void getPdfreport(SearchInputs inputs, HttpServletResponse response) throws Exception {
		List<SearchResults> listCourse = showAllFiltersResults(inputs); 
		
		Document doc = new Document(PageSize.A4);
		PdfWriter.getInstance(doc,response.getOutputStream());
		doc.open();
		com.lowagie.text.Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		font.setSize(25);
		font.setColor(Color.CYAN);
		Paragraph para = new Paragraph(" SEARCH RESULTS",font);
		para.setAlignment(Paragraph.ALIGN_CENTER);
		doc.add(para);
		PdfPTable table = new PdfPTable(10);
		table.setWidthPercentage(60);
		table.setWidths(new float[] {3.0f,3.0f,3.0f,3.0f,3.0f,3.0f,3.0f,3.0f,3.0f,3.0f});
		table.setSpacingBefore(4.0f);
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.GRAY);
		cell.setPadding(4);
		com.lowagie.text.Font cellFont = FontFactory.getFont(FontFactory.COURIER_BOLD);
		cell.setPhrase(new Phrase("COURSE_ID",cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("COURSE_NAME",cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("COURSE_CATAGORY",cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("FACULTY_NAME",cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("LOCATION",cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("FEE",cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("COURSE_STATUS",cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("TRAINING_MODE",cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("ADMIN_CONTACT",cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("START_DATE",cellFont));
		table.addCell(cell);
		
		listCourse.forEach(result->{
			table.addCell(String.valueOf(result.getCourseId()));
			table.addCell(String.valueOf(result.getCourseName()));
			table.addCell(String.valueOf(result.getCourseCatagory()));
			table.addCell(String.valueOf(result.getFacultyName()));
			table.addCell(String.valueOf(result.getLocation()));
			table.addCell(String.valueOf(result.getCourseFee()));
			table.addCell(String.valueOf(result.getCourseStatus()));
			table.addCell(String.valueOf(result.getTrainingMode()));
			table.addCell(String.valueOf(result.getAdminContact()));
			table.addCell(String.valueOf(result.getStartDate()));
		});
		doc.add(table);
		doc.close();
		
	}

	
    @Override
	public String getAllPdfreport(HttpServletResponse response) throws Exception {
		List<CourseDetails> list = repo.findAll();	
        List<SearchResults> listSearch = new  ArrayList<>();
	   	list.forEach(itera->{
			SearchResults results = new SearchResults();
			BeanUtils.copyProperties(itera, results);
			listSearch.add(results);
		});
	   	
	   	Document doc = new Document(PageSize.A4);
		PdfWriter.getInstance(doc,response.getOutputStream());
		doc.open();
		com.lowagie.text.Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		font.setSize(25);
		font.setColor(Color.CYAN);
		Paragraph para = new Paragraph(" SEARCH RESULTS",font);
		para.setAlignment(Paragraph.ALIGN_CENTER);
		doc.add(para);

		PdfPTable table = new PdfPTable(10);		
		table.setWidthPercentage(60);
		table.setWidths(new float[] {3.0f,3.0f,3.0f,3.0f,3.0f,3.0f,3.0f,3.0f,3.0f,3.0f});
		table.setSpacingBefore(4.0f);
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.GRAY);
		cell.setPadding(4);
		com.lowagie.text.Font cellFont = FontFactory.getFont(FontFactory.COURIER_BOLD);
		cell.setPhrase(new Phrase("COURSE_ID",cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("COURSE_NAME",cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("COURSE_CATAGORY",cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("FACULTY_NAME",cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("LOCATION",cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("FEE",cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("COURSE_STATUS",cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("TRAINING_MODE",cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("ADMIN_CONTACT",cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("START_DATE",cellFont));
		table.addCell(cell);
		
		listSearch.forEach(result->{
			table.addCell(String.valueOf(result.getCourseId()));
			table.addCell(String.valueOf(result.getCourseName()));
			table.addCell(String.valueOf(result.getCourseCatagory()));
			table.addCell(String.valueOf(result.getFacultyName()));
			table.addCell(String.valueOf(result.getLocation()));
			table.addCell(String.valueOf(result.getCourseFee()));
			table.addCell(String.valueOf(result.getCourseStatus()));
			table.addCell(String.valueOf(result.getTrainingMode()));
			table.addCell(String.valueOf(result.getAdminContact()));
			table.addCell(String.valueOf(result.getStartDate()));
		});
		doc.add(table);
		doc.close();
	   	
	   	
		return "PDF-GENERATED";
	}
    

	@Override
	public void getExcelreport(SearchInputs inputs, HttpServletResponse response) throws Exception {
		List<SearchResults> list = showAllFiltersResults(inputs);
		
		HSSFWorkbook workBook = new HSSFWorkbook();
		HSSFSheet sheet = workBook.createSheet("CourseDetails");
		
		HSSFRow row  = sheet.createRow(0);
		row.createCell(0).setCellValue("COURSE_ID");
		row.createCell(1).setCellValue("COURSE_NAME");
		row.createCell(2).setCellValue("COURSE_CATAGORY");
		row.createCell(3).setCellValue("FACULTY_NAME");
		row.createCell(4).setCellValue("LOCATION");
		row.createCell(5).setCellValue("FEE");
		row.createCell(6).setCellValue("COURSE_STATUS");
		row.createCell(7).setCellValue("TRAINING_MODE");
		row.createCell(8).setCellValue("ADMIN_CONTACT");
		row.createCell(9).setCellValue("START_DATE");
		
		int i=1;
		
		for(SearchResults result:list) {
			HSSFRow dataRow = sheet.createRow(i);
			dataRow.createCell(0).setCellValue(result.getCourseId());
			dataRow.createCell(1).setCellValue(result.getCourseName());
			dataRow.createCell(2).setCellValue(result.getCourseCatagory());
			dataRow.createCell(3).setCellValue(result.getFacultyName());
			dataRow.createCell(4).setCellValue(result.getLocation());
			dataRow.createCell(5).setCellValue(result.getCourseFee());
			dataRow.createCell(6).setCellValue(result.getCourseStatus());
			dataRow.createCell(7).setCellValue(result.getTrainingMode());
			dataRow.createCell(8).setCellValue(result.getAdminContact());
			dataRow.createCell(9).setCellValue(result.getStartDate().toString());
			i++;
		}
		
		ServletOutputStream out = response.getOutputStream();
		workBook.write(out);
		out.close();
		workBook.close();
		
	}
    
	@Override
	public String getAllExcelreport(HttpServletResponse response) throws Exception {
		List<CourseDetails> list = repo.findAll();	
        List<SearchResults> listSearch = new  ArrayList<>();
	   	list.forEach(itera->{
			SearchResults results = new SearchResults();
			BeanUtils.copyProperties(itera, results);
			listSearch.add(results);
		});
	   	
	   	HSSFWorkbook workBook = new HSSFWorkbook();
		HSSFSheet sheet = workBook.createSheet("CourseDetails");
		
		HSSFRow row  = sheet.createRow(0);
		row.createCell(0).setCellValue("COURSE_ID");
		row.createCell(1).setCellValue("COURSE_NAME");
		row.createCell(2).setCellValue("COURSE_CATAGORY");
		row.createCell(3).setCellValue("FACULTY_NAME");
		row.createCell(4).setCellValue("LOCATION");
		row.createCell(5).setCellValue("FEE");
		row.createCell(6).setCellValue("COURSE_STATUS");
		row.createCell(7).setCellValue("TRAINING_MODE");
		row.createCell(8).setCellValue("ADMIN_CONTACT");
		row.createCell(9).setCellValue("START_DATE");
		
		int i=1;
		
		for(SearchResults result:listSearch) {
			HSSFRow dataRow = sheet.createRow(i);
			dataRow.createCell(0).setCellValue(result.getCourseId());
			dataRow.createCell(1).setCellValue(result.getCourseName());
			dataRow.createCell(2).setCellValue(result.getCourseCatagory());
			dataRow.createCell(3).setCellValue(result.getFacultyName());
			dataRow.createCell(4).setCellValue(result.getLocation());
			dataRow.createCell(5).setCellValue(result.getCourseFee());
			dataRow.createCell(6).setCellValue(result.getCourseStatus());
			dataRow.createCell(7).setCellValue(result.getTrainingMode());
			dataRow.createCell(8).setCellValue(result.getAdminContact());
			dataRow.createCell(9).setCellValue(result.getStartDate().toString());
			System.out.println(result.getStartDate().toString());
			i++;
		}
		
		ServletOutputStream out = response.getOutputStream();
		workBook.write(out);
		out.close();
		workBook.close();
	   	
		return "EXCEL-GENERATED";
	}
	

}
