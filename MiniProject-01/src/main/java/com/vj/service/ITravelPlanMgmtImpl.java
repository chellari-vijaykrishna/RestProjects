package com.vj.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vj.commons.TravelPlanConstants;
import com.vj.config.AppConfig;
import com.vj.entity.PlanCatagory;
import com.vj.entity.TravelPlan;
import com.vj.repo.IPlanCatagoryRepo;
import com.vj.repo.ITravelPlanRepo;


@Service("ITravelPlanMgmtImpl")
public class ITravelPlanMgmtImpl implements ITravelPlanMgmtService {
	
	@Autowired
	private ITravelPlanRepo travelRepo;
	@Autowired
	private IPlanCatagoryRepo planRepo;

	private Map<String, String> messages;
	

	public ITravelPlanMgmtImpl (AppConfig props){
		messages=props.getMessages();
	}

	@Override
	public String registerTravelPlan(TravelPlan plan) {	
		TravelPlan savedTravelPlan=	travelRepo.save(plan);
		return savedTravelPlan!=null?messages.get(TravelPlanConstants.SAVE_SUCESS)+savedTravelPlan.getPlanId():messages.get(TravelPlanConstants.SAVE_FAIL);
	}

	@Override
	public Map<Integer, String> getTravelPlanCata() {
		List<PlanCatagory> planCata = planRepo.findAll();
		Map<Integer, String> map = new HashMap<Integer, String>();
		planCata.forEach(list->{
			map.put(list.getCatagoryId(),list.getCatagoryName());
		});
		return map;
	}

	@Override
	public List<TravelPlan> showTravelPlan() {
		List<TravelPlan> plan = travelRepo.findAll();
		return plan;
	}

	@Override
	public TravelPlan showPlanById(Integer id) {
		return travelRepo.findById(id).orElseThrow(()->new IllegalArgumentException(messages.get(TravelPlanConstants.FIND_FAIL)));
	}

	@Override
	public String updateTravelPlan(TravelPlan plan) {
		
		Optional<TravelPlan> opt = travelRepo.findById(plan.getPlanId());
		if(opt.isPresent()) {
			TravelPlan plan1 =opt.get();
			plan1.setActiveStatus(plan.getActiveStatus());
			travelRepo.save(plan1);
		return messages.get(TravelPlanConstants.UPDATE_SUCESS);
		}
		else {
			return messages.get(TravelPlanConstants.UPDATE_FAIL);
		}
	}

	@Override
	public String deleteTravelPlan(Integer id) {
		Optional<TravelPlan> opt = travelRepo.findById(id);
		if(opt.isPresent()) {
			travelRepo.deleteById(id);
		return messages.get(TravelPlanConstants.DELETE_SUCESS);
		}
		else {
			return messages.get(TravelPlanConstants.DELETE_FAIL);
		}
	}

	@Override
	public String changeTravelPlanStatus(Integer planid, String status) {
		System.out.println(planid+status);
		Optional<TravelPlan> opt = travelRepo.findById(planid);
			if(opt.isPresent()) {
				System.out.println(opt.toString());
				TravelPlan plan =opt.get();
				plan.setActiveStatus(status);
				travelRepo.save(plan);
				System.out.println(opt.toString());
			return messages.get(TravelPlanConstants.ACTIVE_CHANGED_SUCESS);
			}
			else {
				return messages.get(TravelPlanConstants.ACTIVE_CHANGED_FAIL);
			}
	}

}
