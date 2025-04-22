package com.vj.service;

import java.util.List;
import java.util.Map;

import com.vj.entity.TravelPlan;

public interface ITravelPlanMgmtService {
	
	public String registerTravelPlan(TravelPlan plan);
	
	public Map<Integer, String> getTravelPlanCata();
	
	public List<TravelPlan> showTravelPlan();
	
	public TravelPlan showPlanById(Integer id);
	
	public String updateTravelPlan(TravelPlan plan);
	
	public String deleteTravelPlan(Integer id);
	
	public String changeTravelPlanStatus(Integer planid, String status);

}
