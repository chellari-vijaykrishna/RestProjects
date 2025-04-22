package com.vj.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
@Entity
@Table(name = "TRAVEL_PLAN")
public class TravelPlan {
	
	@Id
	@SequenceGenerator(name = "seq11",initialValue = 1,allocationSize = 1)
	@GeneratedValue(generator = "gen11",strategy = GenerationType.SEQUENCE)
	@Column(name = "PLAN_ID")
	private Integer planId;
	
	@Column(name = "PLAN_NAME",length = 20)
	private String planName;
	
	@Column(name = "PLAN_MIN_BUDGET")
	private Double planMinBudget;
	
	@Column(name = "PLAN_CATAGORY",length = 20)
	private String planCatagory;
	
	@Column(name = "ACTIVE_STATUS",length = 20)
	private String activeStatus;
	
	@Column(name = "CREATED_BY",length = 20)
	private String createdBy;
	
	@Column(name = "UPDATED_BY",length = 20)
	private String updatedBy;
	
	@Column(name="CREATED_DATE",updatable = false)
	@CreationTimestamp
	private LocalDateTime createdDate;
	
	@Column(name="UPDATED_DATE",insertable = false,updatable = true)
	@UpdateTimestamp
	private LocalDateTime updatedDate;

	public Integer getPlanId() {
		return planId;
	}

	public void setPlanId(Integer planId) {
		this.planId = planId;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public Double getPlanMinBudget() {
		return planMinBudget;
	}

	public void setPlanMinBudget(Double planMinBudget) {
		this.planMinBudget = planMinBudget;
	}

	public String getPlanCatagory() {
		return planCatagory;
	}

	public void setPlanCatagory(String planCatagory) {
		this.planCatagory = planCatagory;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}


	
	@Override
	public String toString() {
		return "TravelPlan [planId=" + planId + ", planName=" + planName + ", planMinBudget=" + planMinBudget
				+ ", planCatagory=" + planCatagory + ", activeStatus=" + activeStatus + ", createdBy=" + createdBy
				+ ", updatedBy=" + updatedBy + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + "]";
	}

	public TravelPlan() {
	}
	

}
