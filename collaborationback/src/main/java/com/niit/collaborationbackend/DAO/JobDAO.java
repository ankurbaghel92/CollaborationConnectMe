package com.niit.collaborationbackend.DAO;

import java.util.List;

import com.niit.collaborationbackend.model.Job;

public interface JobDAO {
	
public boolean save(Job job);
	
	public boolean delete(Job job);
	
	public boolean update(Job job);
	
	public Job get(String id);
	
	public List<Job> list();


}
