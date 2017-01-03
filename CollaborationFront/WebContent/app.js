var app = angular.module('myapp',['ngRoute']);

app.config(function($routeProvider){
	$routeProvider
	
	.when('/bloghome',{
		templateUrl:'c_blog/blogHome.html'
	})
	
	.when('/createblog',{
		templateUrl:'c_blog/createBlog.html'
	})

		.when('/jobHome',{
		templateUrl:'c_job/jobHome.html',
		controller:'JobController'
	})
	
		.when('/createjob',{
		templateUrl:'c_job/createJob.html'
	})
	
		.when('/eventhome',{
		templateUrl:'c_event/eventHome.html'
			
	})
	
		.when('/createevent',{
		templateUrl:'c_event/createEvent.html'
			
	})
	
	
	
	.when('/grouphome',{
		templateUrl:'c_forum/groupHome.html'
			
	})
	
		.when('/createforum',{
		templateUrl:'c_forum/createForum.html'
			
	})
	
	
	
	
	
	
	
		.when('/login',{
			
			templateUrl:'login.html',
			controller:'LoginController'

		})

		.when('/logout',{
			
			templateUrl:'logout.html',
			controller:'LogoutController'

		})

	

})