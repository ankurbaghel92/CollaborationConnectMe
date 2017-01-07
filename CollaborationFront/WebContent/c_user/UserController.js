/*"use strict"
*//*
app.controller('UserController',['$scope','UserServices','$location','$rootScope','$cookieStore','$http',
                                 function($scope, $UserServices, $location, $rootScope, $cookieStore, $http){
	*/
/*app.controller('UserController',function($scope){

	var self=this;

	
	self.user = {fname : '', lname : '', emailId : '', password : '', mobile : '', role : '', isOnline : '', status : '', errorCode : '',
			 errorMessage : '', gender : '', reason: ''	};
	

$scope.message="Message From User Contoller"
	
	
	self.reset = function(){
	self.user = {
	fname : '',
	lname : '',
	emailId : '',
	password : '',
	mobile : '',
	role : '',
	isOnline : '',
	status : '',
	errorCode : '',
    errorMessage : '',
    gender : '',
	reason: ''
	}

}
}
);*/
	

app.controller('UserController',['$scope','UserServices','$location','$rootScope','$http',
                                 function($scope, $UserServices, $location, $rootScope, $http){

										var self =this;
									
										self.user = {fname : '', lname : '', emailId : '', password : '', mobile : '', role : '', isOnline : '', status : '', errorCode : '',
													 errorMessage : '', gender : '', reason: ''	};
										
										self.users = [];
										
										
										//Start of fetchAllUsers function()
										self.fetchAllUsers = function(){
											UserServices.fetchAllUsers().then
											    (
														function(d){
															self.users=d;
														},
														function(errResponse)
														{
															console.error("Error  While Getting all the Users detials,.,.,.")
														}
												)
										};//end of fetchAllUsers function()
									
										
										
										
										//start of createUser function()
										self.createUser = function(user){
											//console
											UserServices.createUser(user).then
											(
													function(d)
													{
														alert("ThankYou For Registration,.,,,,")
														//location
													},
													function(errResponse)
													{
														console.error("Error While Registration,,., Please try again after some time,.,")
													}
											)
										};//end of createUser function()
										
										
										
										
										//start of myProfile function()
										self.myProfile = function(){
											UserServices.myProfile().then
											(
													function(d)
													{
														self.user=d;
													},
													function(errResponse)
													{
														console.error("Error While Fetching Profile,.,..,.")
													}
											)
										}//end of myProfile function()
										
										
										
										
										
										//start of submit function()
										self.submit = function() {
											self.createUser(self.user);
											self.reset();
										};//end of submit function()
										
										
										
										
										
										//start of reset function
										self.reset = function(){
											self.user = {
											fname : '',
											lname : '',
											emailId : '',
											password : '',
											mobile : '',
											role : '',
											isOnline : '',
											status : '',
											errorCode : '',
										    errorMessage : '',
										    gender : '',
											reason: ''
											}
										};//end of reset funtion()
											
}])