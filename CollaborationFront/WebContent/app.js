var app = angular.module('myapp',['ngRoute']);

app.config(function($routeProvider){
	$routeProvider
	
	.when('/',{
		templateUrl:'c_user/home.html'
	})
	
	.when('/home',{
		templateUrl:'c_user/home.html'
	})

	
		.when('/users',{
		templateUrl:'c_user/users.html'
	})

	
	.when('/login',{
		templateUrl:'c_user/login.html',
			controller:'UserController'
	})

	.when('/register',{
		templateUrl:'c_user/register.html'
	})

	
	/*.when('/logout',{
			
			templateUrl:'c_user/register.html',
			controller:'UserController'

		})

*/	
	.when('/bloghome',{
		templateUrl:'c_blog/blogHome.html'
	})
	
	.when('/createblog',{
		templateUrl:'c_blog/createBlog.html',
			controller:'BlogController'

	})

	
	
	
		.when('/jobHome',{
		templateUrl:'c_job/jobHome.html',
		controller:'JobController'
	})
	
		.when('/openJobs',{
		templateUrl:'c_job/openJobs.html'
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
	
	
	
	
	
	
	
		/*.when('/login',{
			
			templateUrl:'login.html',
			controller:'LoginController'

		})
*/
		
	

})
