package com.niit.collaborationbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.niit.collaborationbackend.DAOIMPL.JobDAOIMPL;
import com.niit.collaborationbackend.model.Job;

@Component
public class JobController {

	@Autowired
	Job job;
	
	@Autowired
	JobDAOIMPL jobDAO;
	
	
	
	@RequestMapping("/allJobs")
	public ResponseEntity<List<Job>> getAllJobs(){
		
		List<Job> jobs = jobDAO.list();
		if(jobs==null){
			job.setErrorCode("404");
			job.setErrorMessage("No Jobs Were found");
			jobs.add(job);
		}
		
		return new ResponseEntity<List<Job>>(jobs,HttpStatus.OK);
	}
	
	@RequestMapping("/jobByID/{id}")
	public ResponseEntity<Job> getJobById(@PathVariable("id") String jobId)
	{
		Job job = jobDAO.get(jobId);
		if(job==null)
		{
			job= new Job();
			job.setErrorCode("404");
			job.setErrorMessage("Error while getting job by Id = "+jobId);
		}
		return new ResponseEntity<Job>(job, HttpStatus.OK);
	}
	
	@RequestMapping("/savejob")
	public ResponseEntity<Job> saveJob(@RequestBody Job job)
	{
		if(jobDAO.save(job)==false)
		{
			job.setErrorCode("404");
			job.setErrorMessage("Error while saving Job,., Please try again after sometime,.,!!,.,!!,.");
		}
		else
		{
			job.setErrorCode("200");
			job.setErrorMessage("Thankyou!! Job has beed posted Successfully,.,!!.,.!!!,.,");
		}
		return new ResponseEntity<Job>(job,HttpStatus.OK);
	}
	
	@RequestMapping("/updatejob")
	public ResponseEntity<Job> updateJob(@RequestBody Job job){
		if(jobDAO.update(job)==false){
			job.setErrorCode("404");
			job.setErrorMessage("Error while update Job,., Please try again after sometime,.,!!,.,!!,.,");
		}
		else
		{
			job.setErrorCode("200");
			job.setErrorMessage("Thankyou!! Job has been update Successfully,.,!!,.,!!,.,");
		}
		
		return new ResponseEntity<Job>(job,HttpStatus.OK);
	}
	
}