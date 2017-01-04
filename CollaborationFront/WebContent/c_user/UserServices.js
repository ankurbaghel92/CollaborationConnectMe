"use strict"
app.factory('UserServices','http','$q','$rootScope',function($http,$q,$rootScope){
	
	var BaseURL='http://localhost:8088/collaborationback'
	
		return{
		
		fetchAllUsers:function(){
			return $http.get(BaseUrl+'/allUsers')
			.then(
					function(Response){
						return Response.data;
					},
					null
				)
		},//end of fetchAllUser:function()
	
	createUser:function(user){
		return $http.post(BaseURL+'/registerUser/',user)
		.then(
				function(Response){
					return Response.data;
				},
				function(errResponse){
					return $q.reject(errResponse);
				}
				
		);
		
	},//end of createUser function()
	
	updateUser:function(user){
		return $http.put(BaseURL+'/updateUser/'+user)
		.then(
			function(Response)
			{
				return Response.data
			},
			function(errResponse)
			{
				return $q.reject(errResponse);
			}
			
		);
	},//end of updateUser function()
	
	accept:function(id){
		return $http.get(BaseURL+'/userById'+id)
		.then(
				function(Response)
				{
					return Response.data;
				},
				function(errResponse)
				{
					return $q.reject(errResponse);
				}
		
		);

	},//end of accept function()
	
	authenticate:function(user){
		return $http.post(BaseURL+'/authenticate/',user)
		.then(
				function(Response){
					return Response;
				},
				function(errResponse)
				{
					return $q.reject(errResponse);
				}
		);
	}
	
	}
	
})