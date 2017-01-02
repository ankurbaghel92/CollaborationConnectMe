package com.niit.collaborationbackend.model;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class Job extends BaseDomain {
	
	private int Id;
	
	private String tittle;
	
	private String qualificaton;
	
	private String description;
	
	private char status;
	
	private Date date;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getTittle() {
		return tittle;
	}

	public void setTittle(String tittle) {
		this.tittle = tittle;
	}

	public String getQualificaton() {
		return qualificaton;
	}

	public void setQualificaton(String qualificaton) {
		this.qualificaton = qualificaton;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
	

}
