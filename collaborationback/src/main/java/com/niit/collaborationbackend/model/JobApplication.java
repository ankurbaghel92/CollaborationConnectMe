package com.niit.collaborationbackend.model;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class JobApplication extends BaseDomain {
	
	private String Id;
	
	private String emailId;
	
	private String jobId;

	private Date date_Applied;
	
	private char status;
	
	private String remarks;

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public Date getDate_Applied() {
		return date_Applied;
	}

	public void setDate_Applied(Date date_Applied) {
		this.date_Applied = date_Applied;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
	
	
}
