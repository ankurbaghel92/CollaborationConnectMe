package com.niit.collaborationbackend.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collaborationbackend.DAO.JobApplicationDAO;
import com.niit.collaborationbackend.DAOIMPL.JobApplicationDAOIMPL;
import com.niit.collaborationbackend.model.JobApplication;

@RestController
public class JobApplicationController {

	@Autowired
	JobApplication jobApplication;
	
	@Autowired
	JobApplicationDAO jobApplicationDAO;
	
	@Autowired(required=false)
	HttpSession session;
	
	//Get List Of ALL JobApplications
	@RequestMapping("/allJobApplications")
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


	
	//Get JobApplication By Id----->
	@RequestMapping("/jobApplicationById/{id}")
	public ResponseEntity<JobApplication> getJobApplicationByID(@PathVariable("id") String jobApplicationId)
	{
		 jobApplication = jobApplicationDAO.getJobApplication(jobApplicationId);
		if(jobApplication==null)
		{
			jobApplication  = new JobApplication();
			jobApplication.setErrorCode("404");
			jobApplication.setErrorMessage("No JobApplications Were Found");
		}
		
		return new ResponseEntity<JobApplication>(jobApplication, HttpStatus.OK);
	}
	
	
	
	//To Apply for a Job
	@RequestMapping(value="/applyForJob/{jobId}",method=RequestMethod.POST)
	public ResponseEntity<JobApplication> applyForJob(@PathVariable("jobId") String jobId, @RequestBody JobApplication jobApplication,HttpSession session)
	{
		String emailId = (String) session.getAttribute("loggedInUserID");
		System.out.println(emailId);
		
		//User is not logged in
		if(emailId==null)
		{
			jobApplication.setErrorCode("404");
			jobApplication.setErrorMessage("You are not Logged In.,. Please login,.,");
		}
		else
		{		//if user is logged in and to check if not already applied for the job
				if(jobApplicationDAO.getJobApplication(emailId, jobId)==null)
					{
						jobApplication.setJobId(jobId);
						jobApplication.setStatus('N');		//N-->New		S-->Selected		C-->Call For Interview
						jobApplication.setEmailId(emailId);
						jobApplication.setDate_Applied(new Date());
						jobApplication.setId(jobApplicationDAO.maxID());
						//if the saving jobApplication is successfull
						if(jobApplicationDAO.saveJobApplication(jobApplication));
							{
								jobApplication.setErrorCode("200");
								jobApplication.setErrorMessage("Thank you !!..!!..You have SuccessFully Applied for this job"+jobId);
							}
					}
				//if user is logged in and to check if already applied for the job
				else
				{
					jobApplication.setErrorCode("404");
					jobApplication.setErrorMessage("You have already applied for a job.. !! ..!! ..");
				}
		
		}
		
		return new ResponseEntity<JobApplication>(jobApplication, HttpStatus.OK);
	}
	
	
	//if a person wants to see his all Applied Jobs
	@RequestMapping(value="/myAppliedJobs", method=RequestMethod.GET)
	public ResponseEntity<List<JobApplication>> myAppliedJobs(HttpSession session){
		String emailId = (String) session.getAttribute("emailId");
		List<JobApplication> jobApplications = new ArrayList<JobApplication>();
		if(emailId==null)
		{
			jobApplication.setErrorCode("404");
			jobApplication.setErrorMessage("We are Sorry!!,., You are not Logged in,.,.");
		}
		else
		{
		
		 jobApplications  = jobApplicationDAO.myAppliedJob(emailId);
		
	
		}
		return new ResponseEntity<List<JobApplication>>(jobApplications,HttpStatus.OK);

		}
	
	
	//If a Person is selected for a Job
	@RequestMapping(value="/selectJobApplication/{emailId}/{jobId}/{remarks}",method=RequestMethod.PUT)
	public ResponseEntity<JobApplication> selectJobApplication(@PathVariable("emailId") String emailId, @PathVariable("jobId") String jobId,
			@PathVariable("remarks") String remarks)
	{

			jobApplication =updateJobApplication(emailId, jobId,'S', remarks);
		
		return new ResponseEntity<JobApplication>(jobApplication, HttpStatus.OK);
	}
	

	
	
	//If a Person is rejected for a Job
	@RequestMapping(value="/rejectJobApplication/{emailId}/{jobId}/{remarks}",method=RequestMethod.PUT)
	public ResponseEntity<JobApplication> rejectJobApplication(@PathVariable("emailId") String emailId, @PathVariable("jobId") String jobId,
			@PathVariable("remarks") String remarks)
	{

			jobApplication =updateJobApplication(emailId, jobId,'R', remarks);
		
		return new ResponseEntity<JobApplication>(jobApplication, HttpStatus.OK);
	}
	
	
	
	//If a Person has been called for an Interview
	@RequestMapping(value="/callForInterview/{emailId}/{jobId}/{remarks}",method=RequestMethod.PUT)
	public ResponseEntity<JobApplication> callForInterview(@PathVariable("emailId") String emailId, @PathVariable("jobId") String jobId,
			@PathVariable("remarks") String remarks)
	{

			jobApplication =updateJobApplication(emailId, jobId,'C', remarks);
		
		return new ResponseEntity<JobApplication>(jobApplication, HttpStatus.OK);
	}


	
	//Private method to minimize the Code
	private JobApplication updateJobApplication(String emailId, String jobId, char status, String remarks)
	{
		if(isUserAppliedForJob(emailId, jobId)==false)
		{
			jobApplication.setErrorCode("404");
			jobApplication.setErrorMessage(emailId+" Not Applied for the job");
			return jobApplication;
		}
		
		
		String loggedInUserRole = (String) session.getAttribute("loggedInUserRole");
		if(loggedInUserRole==null || loggedInUserRole.isEmpty())
		{
			jobApplication.setErrorCode("404");
			jobApplication.setErrorMessage("you are not authorized to process this,.,!!,.,!!");
			return jobApplication;

		}
		
		if(!loggedInUserRole.equals("Admin"))
		{
			jobApplication.setErrorCode("404");
			jobApplication.setErrorMessage("We are Sorry!!,., You are not authorized to process this,.,.");
			return jobApplication;
		}
		
		jobApplication = jobApplicationDAO.getJobApplication(emailId, jobId);
		jobApplication.setStatus(status);
		jobApplication.setRemarks(remarks);
		if(jobApplicationDAO.updateJobApplication(jobApplication))
		{
			jobApplication.setErrorCode("200");
			jobApplication.setErrorMessage("Status has been changed to "+status);
		}
		else
		{
			jobApplication.setErrorCode("404");
			jobApplication.setErrorMessage("We are Sorry!!,., SomeThing went wrong,.,Unable to update the status");

		}
		return jobApplication;
	}
	
	
	private boolean isUserAppliedForJob(String emailId, String jobId)
	{
		if(jobApplicationDAO.getJobApplication(emailId, jobId)==null)
			return false;
		else
			return true;
	}
	
	
	
	

}








