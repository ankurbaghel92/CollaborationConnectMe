var app = angular.module('myapp',['ngRoute']);

app.config(function($routeProvider){
	$routeProvider

		.when('/job',{
		templateUrl:'job.html',
		controller:'JobController'
	})
	
		.when('/blog',{
		templateUrl:'blog.html'
	})
	
		.when('/friend',{
		templateUrl:'friend.html'
			
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
