package com.niit.collaborationbackend.model;

import javax.persistence.*;

import org.springframework.stereotype.Component;

@Entity
@Table(name="friend")
@Component
public class Friend extends BaseDomain {
	
	@Id
	private int Id;
	
	private String emailID;
	
	private String friendEmailId;
	
	private char status;
	
	@Column(name="IS_ONLINE")
	private char isOnline;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	public String getFriendEmailId() {
		return friendEmailId;
	}

	public void setFriendEmailId(String friendEmailId) {
		this.friendEmailId = friendEmailId;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public char getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(char isOnline) {
		this.isOnline = isOnline;
	}
	
	
	

}
