package com.vj.ms;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vj.entity.TravelPlan;
import com.vj.service.ITravelPlanMgmtService;



@RestController
@RequestMapping("travel/api")
public class TravelRestController {
	
	@Autowired
	private ITravelPlanMgmtService service;
	
	@GetMapping("/show-report-cata")
	public ResponseEntity<?> showTravelPlans(){
		try {
			Map<Integer, String> map = service.getTravelPlanCata();
			return new ResponseEntity<Map<Integer,String>>(map,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/registerTravelPlan")
	public ResponseEntity<String> saveTravelPlan(@RequestBody TravelPlan plan){
		try {
			System.out.println(plan.toString());
			  String res = service.registerTravelPlan(plan);
			return new ResponseEntity<String>(res,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/display-travel-plan")
	public ResponseEntity<?> displayAllTravelPlans(){
		try {
			  List<TravelPlan> list = service.showTravelPlan();
			return new ResponseEntity<List<TravelPlan>>(list,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<?> displayTravelPlanById(@PathVariable Integer id){
		try {
			  TravelPlan list = service.showPlanById(id);
			return new ResponseEntity<TravelPlan>(list,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/update-travel")
	public ResponseEntity<String> updateTravelPlan(@RequestBody TravelPlan plan){
		try {
			  String list = service.updateTravelPlan(plan);
			return new ResponseEntity<String>(list,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/delete/{id}")
	public ResponseEntity<String> deleteTravelPlan(@PathVariable Integer id){
		try {
			  String list = service.deleteTravelPlan(id);
			return new ResponseEntity<String>(list,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/change-status/{id}/{status}")
	public ResponseEntity<String> changeActiveStatus(@PathVariable Integer id, @PathVariable String status){
		try {
			  String list = service.changeTravelPlanStatus(id, status);
			return new ResponseEntity<String>(list,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}