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
@Table(name="PLAN_CATAGORY")
public class PlanCatagory {
	
	@Id
	@SequenceGenerator(name="seq1",sequenceName = "cata_seq",initialValue = 1,allocationSize =1)
	@GeneratedValue(generator ="seq1",strategy = GenerationType.SEQUENCE)
	@Column(name="CATAGORY_ID")
	private Integer catagoryId;
	
	@Column(name = "CATAGORY_NAME", length = 20)
	private String catagoryName;
	
	@Column(name="ACTIVE_STATUS",length = 10)
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

	public Integer getCatagoryId() {
		return catagoryId;
	}

	public void setCatagoryId(Integer catagoryId) {
		this.catagoryId = catagoryId;
	}

	public String getCatagoryName() {
		return catagoryName;
	}

	public void setCatagoryName(String catagoryName) {
		this.catagoryName = catagoryName;
	}

	public String getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
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

	@Override
	public String toString() {
		return "PlanCatagory [catagoryId=" + catagoryId + ", catagoryName=" + catagoryName + ", activeStatus="
				+ activeStatus + ", createdBy=" + createdBy + ", updatedBy=" + updatedBy + ", createdDate="
				+ createdDate + ", updatedDate=" + updatedDate + "]";
	}

	public PlanCatagory(Integer catagoryId, String catagoryName, String activeStatus, String createdBy,
			String updatedBy, LocalDateTime createdDate, LocalDateTime updatedDate) {
		super();
		this.catagoryId = catagoryId;
		this.catagoryName = catagoryName;
		this.activeStatus = activeStatus;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}

	public PlanCatagory() {

	}

}
