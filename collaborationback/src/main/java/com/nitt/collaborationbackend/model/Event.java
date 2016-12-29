package com.nitt.collaborationbackend.model;

import java.util.Date;

public class Event {
	
	private int Id;
	
	private String name;
	
	private Date Date_Time;
	
	private String venue;
	
	private String description;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate_Time() {
		return Date_Time;
	}

	public void setDate_Time(Date date_Time) {
		Date_Time = date_Time;
	}

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
