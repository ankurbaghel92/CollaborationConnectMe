package com.niit.collaborationbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collaborationbackend.DAO.JobApplicationDAO;
import com.niit.collaborationbackend.model.JobApplication;

@RestController
public class JobApplicationController {

	@Autowired
	JobApplication jobApplication;
	
	@Autowired
	JobApplicationDAO jobApplicationDAO;
	
	//Get List Of ALL JobApplications
	@RequestMapping("allJobApplications")
	public ResponseEntity<List<JobApplication>> getAllJobApplication()
	{
		List<JobApplication> jobApplications = jobApplicationDAO.list();
		
		if(jobApplications.isEmpty())
		{
			jobApplication.setErrorCode("404");
			jobApplication.setErrorMessage("No JobApplications Were Found");
			jobApplications.add(jobApplication);
		}
		
		return new ResponseEntity<List<JobApplication>>(jobApplications, HttpStatus.OK);
	}


	
	//Get JobApplication By Id
	@RequestMapping("/jobApplicationById/{id")
	public ResponseEntity<JobApplication> getJobApplicationByID(@PathVariable("id") String jobApplicationId)
	{
		 jobApplication = jobApplicationDAO.get(jobApplicationId);
		if(jobApplication==null)
		{
			jobApplication  = new JobApplication();
			jobApplication.setErrorCode("404");
			jobApplication.setErrorMessage("No JobApplications Were Found");
		}
		
		return new ResponseEntity<JobApplication>(jobApplication, HttpStatus.OK);
	}
	
	
	
	//Save a new JobApplication
	@RequestMapping("/savejobApplication")
	public ResponseEntity<JobApplication> saveJobApplication(@RequestBody JobApplication jobApplication)
	{
		if(jobApplicationDAO.save(jobApplication)==false)
		{
			jobApplication.setErrorCode("404");
			jobApplication.setErrorMessage("Some Thing Went Wrong.. !! ..!! .. Please Try Again After Some time..!!..!!..");
		}
		else
		{
			jobApplication.setErrorCode("200");
			jobApplication.setErrorMessage("Thank you !!..!!..You have SuccessFully Applied for this job");
		}
		
		return new ResponseEntity<JobApplication>(jobApplication, HttpStatus.OK);
	}
	
	
	@RequestMapping("/updateJobApplication")
	public ResponseEntity<JobApplication> updateJobApplication(@RequestBody JobApplication jobApplication)
	{
		if(jobApplicationDAO.update(jobApplication)==false)
		{
			jobApplication.setErrorCode("404");
			jobApplication.setErrorMessage("JobApplication was not Updated.. !! ..!! .. Please Try Again After Some time..!!..!!..");
		}
		
		else
		{
			jobApplication.setErrorCode("404");
			jobApplication.setErrorMessage("Thank you !!..!!..JobApplication is updated SuccessFully");
		}
		
		return new ResponseEntity<JobApplication>(jobApplication, HttpStatus.OK);
	}
	

}







