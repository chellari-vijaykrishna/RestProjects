package com.vj.service;

import java.awt.Color;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
import com.vj.entity.CourseDetalis;
import com.vj.model.SearchInputs;
import com.vj.model.SearchResults;
import com.vj.repo.ICourseDetailsRepo;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class ICourseDetailsMgmtServiceImpl implements ICourseDetailsMgmtService {
	
	@Autowired
	private ICourseDetailsRepo repo;

	@Override
	public Set<String> showAllCourseCatagories() {
		return repo.getUniqueCourseCatagories();
	}

	@Override
	public Set<String> showAllTrainingModes() {
		return repo.getUniqueTrainingMode();
	}

	@Override
	public Set<String> showAllFaculties() {
		return repo.getUniqueFacultyNames();
	}

	@Override
	public List<SearchResults> showAllFiltersResults(SearchInputs inputs) {
		CourseDetalis entity = new CourseDetalis();
		String catagory = inputs.getCourseCatagory();
		if(catagory!=null && catagory.equals("")&&catagory.length()==0) {
			entity.setCourseCatagory(catagory);
		}
		String trainingModes = inputs.getTrainigMode();
		if(trainingModes!=null && trainingModes.equals("")&&trainingModes.length()==0) {
			entity.setTrainingMode(trainingModes);
		}
		String facultiesName  = inputs.getFacultyNames();
		if(facultiesName!=null && facultiesName.equals("")&&facultiesName.length()==0) {
			entity.setTrainingMode(facultiesName);
		}
		
		LocalDate startDate = inputs.getStartDate();
		entity.setStartDate(startDate);
		Example<CourseDetalis> example  = Example.of(entity);
		
		List<CourseDetalis> listCourse = repo.findAll(example);
		//Convert CourseDetails to SearchResults 
		
		
		
		List<SearchResults> listSearch = new  ArrayList<>();
		
		listCourse.forEach(itera->{
			SearchResults results = new SearchResults();
			BeanUtils.copyProperties(itera, results);
			listSearch.add(results);
		});
		
		return listSearch;
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
		
		table.setWidths(new float[] {1.0f,1.0f,1.0f,1.0f,1.0f,1.0f,1.0f,1.0f});
		
		table.setSpacingBefore(2.0f);
		
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
	public void getExcelreport(SearchInputs inputs, HttpServletResponse response) {
		
		
	}

}
