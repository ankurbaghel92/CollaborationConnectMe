package com.niit.collaborationbackend.model;

import java.util.Date;

import org.springframework.stereotype.Component;
import javax.persistence.*;

@Entity
@Table(name="job")
@Component
public class Job extends BaseDomain {
	
	@Id
	private Integer Id;
	
	private String tittle;
	
	private String qualification;
	
	private String description;
	
	private Character status;
	
	private Date Date_Time;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getTittle() {
		return tittle;
	}

	public void setTittle(String tittle) {
		this.tittle = tittle;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Character getStatus() {
		return status;
	}

	public void setStatus(Character status) {
		this.status = status;
	}

	public Date getDate() {
		return Date_Time;
	}

	public void setDate(Date Date_Time) {
		this.Date_Time = Date_Time;
	}
	
	
	
	

}
