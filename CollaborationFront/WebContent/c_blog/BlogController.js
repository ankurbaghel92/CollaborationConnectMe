
app.controller('BlogController',['$BlogServices','$http','$q','$location','$rootScope','$scope',function($BlogServices,$http,$q,$location,$rootScoper,$scope){
	
	var self=this;
	
	self.blog= {id:'', tittle:'', emailId:'', Date_time:'', status:'', reason:'', description:''};
	
	self.blogs = [];

	//start of the fetchAllBlogs function()
	self.fetchAllBlogs = function(){
		BlogServices.fetchAllBlogs().then(
				
				function(d){
					self.blogs=d;
				},
				function(errResponse){
					console.log("Error while fetching all the Blogs")
				}
				)
	},//end of the fetchAllBlogs function()
	
	
	//start of the createBlog function()
	self.createBlog = function(blog){
		BlogService.createBlog(blog).then(
		function(d)	{
			alert("Thank you for Creating Blog")
		},
		function(errResponse){
			console.log("Error while Creating Blog,,., Please try Again after sometime,.,.. ")
		}	
		)
			},//end of the createBlog function()
	
			
			
			//start of the getBlog function()
			self.getBlog = function(id){
				BlogServices.getBlog(id).then(
				function(d){
					self.blog=d;
				},
				function(errResponse){
					console.log("Error while fetching the blog")
				}
				
				)
				
			}//end of the getBlogFunction()
}])