"use strict"

app.factory('EventServies','$http','$q','$scope','$rootScope',function($http,$q,$scope,$rootScope){

	var BaseURL='http://localhost:8088/collaborationback'
	
		return{		
			fetchAllEvents : function()
			{
					return $http.get(BaseURL+'/allEvents').then
					(
							function(Response)
							{
								return Reponse.data;
							},
							function(errResponse)
							{
								return $q.reject(errResponse)
							}
					)
			},
			
			createEvent : function(event)
			{
					return $http.post(BaseURL+'/saveEvent/',event).then
					(
						function(Respose)
						{
							return Response.data;
						},
						function(errResponse)
						{
							return $q.reject(errResponse);
						}
						
					)
			},
			
			updateEvent : function(event)
			{
				return $http.put(BaseURL+'/updateEvent/',event).then
				(
						function(Response)
						{
							return Response.data;
						},
						function(errResponse)
						{
							return $q.reject(errResponse)
						}
				
				)
				
			},
			
			eventById : function(id)
			{
				return $http.get(BaseURL+'/eventById/'+id).then
				(
						function(Response)
						{
							return Response.data;
						},
						function(errResponse)
						{
							return $q.reject(errResponse)
						}
				)
			}
			
		
	
	
	
	}
	
	
})












