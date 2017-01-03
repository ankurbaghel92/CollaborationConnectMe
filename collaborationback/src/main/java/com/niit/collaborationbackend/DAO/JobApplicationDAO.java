package com.niit.collaborationbackend.DAO;

import java.util.List;

import com.niit.collaborationbackend.model.JobApplication;

public interface JobApplicationDAO {
	
	public JobApplication get(String Id);
	
	public List<JobApplication> list();
	
	public boolean save(JobApplication jobApplication);
	
	public boolean update(JobApplication jobApplication);

}