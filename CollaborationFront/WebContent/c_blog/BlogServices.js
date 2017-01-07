"use strict"

app.factory('BlogServices', '$http', '$rootScope', '$q',function($http, $rootScope, $q){

	var BaseURL='http://localhost:8088/collaborationback'
	
	return{
		
		//start of fetchAllBlogs function()
		fetchAllBlogs:function(){
			return $http.get(BaseURL+'/allBlogs').then(
					function(Response){
						return Response.data;
					},
					function(errResponse){
						return $q.reject(errResponse);
					}
			)
		},//end of fetchAllBlogs function()
		
		
		
		//start of createBlog function()
		createBlog:function(blog){
			return $http.post(BaseURL+'/saveblog/',user).then(
				function(Response){
					return Response.data;
				},
				function(errResponse){
					$q.reject(errResponse);
				}
			)
		},//end of createBlog function()
		
		
		
		
		//start of getBlog function()
		getBlog:function(id){
			return $http.get(BaseURl+'/blogById'+id).then(
					function(Response){
						return Response.data;
					},
					function(errResponse)
					{
						$q.reject(errResponse)
					}
					
			)
		},//end of getBlog function()
		
		
	}
})


