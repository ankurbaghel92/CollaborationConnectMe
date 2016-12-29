package com.nitt.collaborationbackend.model;

import java.sql.Timestamp;
import java.util.Date;


public class Blog {
	
	
	private int Id;
	
	private String Title;
	
	private String emailId;
	
	private Timestamp Date_Time;
	
	private char status;
	
	private String Reason;
	
	private String Description;
	

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}
	
	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Timestamp getDate_Time() {
		return Date_Time;
	}

	public void setDate_Time(Timestamp date_Time) {
		Date_Time = date_Time;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public String getReason() {
		return Reason;
	}

	public void setReason(String reason) {
		Reason = reason;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}
	
	
	

}
