package com.nitt.collaborationbackend.DAO;

import java.util.List;

import com.nitt.collaborationbackend.model.JobApplication;

public interface JobApplicationDAO {
	
	public JobApplication get(String Id);
	
	public List<JobApplication> list();
	
	public boolean save(JobApplication jobApplication);
	
	public boolean update(JobApplication jobApplication);

}
