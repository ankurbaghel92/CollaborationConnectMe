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
                                 function($scope, UserServices, $location, $rootScope, $http){
	$scope.message="Message From User Contoller"

										var self =this;
									
										self.user = {fname : '', lname : '', emailId : '', password : '', conPassword : '', mobile : '', role : '', isOnline : '', status : '', errorCode : '',
													 errorMessage : '', gender : '', reason : ''	};
										//console.log(self.user.gender)
										
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
											console.log(self.user.gender)
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
										
										
										
										self.authenticate = function(user)
										{
											UserServices.authenticate(user).then
											(
													function(d)
													{
														self.user = d;
														if(self.user.errorCode =="404")
															{
																alert(self.user.errorMessage)
																self.emailId=''
																self.password=''
																
															}
														else
															{
																$rootScope.currentUser = self.user
																console.log("Logging in with Email :- "+$rootScope.currentUser.emailId)
																$rootScope.IsLoggedIn="true"
																$location.path('/createblog')
															}
													}
											)
										}
										
										self.makeAdmin = function(emailId){
											UserServices.makeAdmin(emailId).then
											(
													function(d)
													{
														self.user =  d;
														alert(self.user.errorMessage)
													}											)
										}
										
										self.logoutUser = function()
										{
											UserServices.logout().then
											(
													function(d)
													{	
														$rootScope.IsLoggedIn="false"

															self.user = d;
															alert(self.user.errorMessage)
															$location.path('/login')
													},
													null
											)
										}
										
										
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
											{
											self.createUser(self.user);
											}
											self.reset();

										};//end of submit function()
										
										
										self.login = function()
										{
											self.authenticate(self.user)
										}
										
										self.logout = function()
										{
											self.logoutUser()
										}
										
										
										
										self.fetchAllUsers();
										
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