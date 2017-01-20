'use strict'

app.config('FriendController',['FriendServices','$http','$rootScope','$scope','$location',function(FriendServices,$http,$rootScope,$scope,$location){
	
	$scope.friendmessage="Message From Friend Controller"
		
		var self = this;
		
		self.friend = {Id:'',emailId : '',friendEmailId : '',status : '',IsOnline : '' };
		
		self.friends = [];
		
		self.sendFriendRequest = function(friendEmailId){
			FriendServices.sendFriendRequest(friendEmailId).then
			(
					function(d)
					{
						self.friend=d;
						alert(self.friend.errorMessage)
					},
					function(errResponse)
					{
						console.error("Error  While Sending Friend Request")
					}
			)
			
		},
		
		self.acceptFriendRequest = function(friendEmailId)
		{
			FriendServices.acceptFriendRequest(friendEmailId).then
			(
					
					function(d)
					{
						self.friend=d;
						alert(self.friend.errorMessage)
					},
					
					function(errResponse)
					{
						console.error("Error  While Accepting Friend Request")
					}
					
			)
		}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}])