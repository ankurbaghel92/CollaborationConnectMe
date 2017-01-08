"use strict"

app.controller('JobController',['JobServices','$http','$q','$rootScope','$location','$scope',function($http,$q,$location,$scope,$rootScope)
{
	var self = this;
	
	self.job = {id : '', tittle : '', qualification : '', description : '', status : '', date : ''};
	
	self.jobs = [];
	
	self.fetchAllJobs = function()
	{
		JobServices.fetchAllJobs().then
		(
				function(d)
				{
					self.jobs=d;
				},
				function(errResponse)
				{
					console.log("Error While Fetching all Jobs !!,.,!!,.,.")
				}
		)
	},
	
	
	self.jobByID = function(id)
	{
			JobServices.jobById(id).then
			(
					function(d)
					{
						self.job = d;
					},
					function(errRespose)
					{
						console.log("Error While Fetching Jon ,.,!!,.,!!,.,")
					}
			)
	},
	
	
	self.createJob = function(job)
	{
		JobServices.createJob(job).then
		(
				function(d)
				{
					alert("Thankyou Job has been Posted Successfull")
				},
				function(errResponse)
				{
					console.log("Error while posting job,, please try again after sometime ,.,!!,.,!!,.,")
				}
		)
	}
	
	self.updateJob = function(job)
	{
		JobServices.updateJob(job).then
		(
				function(d)
				{
					alert("Thankyou Job has been udpate Successfully")
				},
				function(errResponse)
				{
					console.log("Error While Updating Job.,.,Please try again after sometime,.,!!,.,!!,.,")
				}
		)
	}
}])

