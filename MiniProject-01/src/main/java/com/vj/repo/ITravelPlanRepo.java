package com.vj.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vj.entity.TravelPlan;

public interface ITravelPlanRepo extends JpaRepository<TravelPlan, Integer> {

}
