package com.niit.collaborationbackend.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collaborationbackend.DAO.FriendDAO;
import com.niit.collaborationbackend.model.Friend;


@RestController
public class FriendController {
	
	@Autowired
	Friend friend;
	
	@Autowired
	FriendDAO friendDAO;
	
	@Autowired(required=false)
	HttpSession session;
	
	public static Logger log = org.slf4j.LoggerFactory.getLogger(FriendController.class);
	
	@RequestMapping(value="/getMyFriendRequest",method=RequestMethod.GET)
	public ResponseEntity<List<Friend>> getMyFriendRequest(HttpSession session)
	{
		log.debug("FriendController ====> Starting of the getMyFriendRequest method()");

		String loggedInUserId = (String) session.getAttribute("loggedInUserId");
		
		List<Friend> friendRequest = new ArrayList<Friend>();
		
		if(loggedInUserId==null)
		{
			friend.setErrorCode("404");
			friend.setErrorMessage("Please Login to Continue,.,");
			friendRequest.add(friend);
			log.debug("FriendController ====> Ending of the getMyFriendRequest method()");
		}
		
		friendRequest= friendDAO.getMyFriendRequests(loggedInUserId);
		
		if(friendRequest.isEmpty())
		{
			friend.setErrorCode("404");
			friend.setErrorMessage("No friends are available,.,!!,.,!!,.,");
			friendRequest.add(friend);
			log.debug("FriendController ====> Ending of the getMyFriendRequest method()");

		}
		
		return new ResponseEntity<List<Friend>>(friendRequest,HttpStatus.OK);
	}
	
	@RequestMapping(value="/getMyFriends",method=RequestMethod.GET)
	public ResponseEntity<List<Friend>> getMyFriends()
	{
		log.debug("FriendController ====> Starting of the getMyFriends method()");

		String loggedInUserId = (String) session.getAttribute("loggedInUserId");
		
		List<Friend> myFriends = new ArrayList<Friend>();
		
		if(loggedInUserId==null)
		{
			friend.setErrorCode("404");
			friend.setErrorMessage("Please Login to Continue,.,");
			myFriends.add(friend);
			
			log.debug("FriendController ====> Ending of the getMyFriends method()");

		}
		
		myFriends=friendDAO.getMyFriends(loggedInUserId);
		
		if(myFriends.isEmpty())
		{
			friend.setErrorCode("202");
			friend.setErrorMessage("Currently You Does Not Have Any Friends Added");
			myFriends.add(friend);
			
			log.debug("FriendController ====> Ending of the getMyFriends method()");

		}
		
		return new ResponseEntity<List<Friend>>(myFriends,HttpStatus.OK);

	}

	
	
	@RequestMapping(value="/sendFriendRequest/{friendId}",method=RequestMethod.GET)
	public ResponseEntity<Friend> sendFriendRequest(@PathVariable("friendId") String friendId)
	{
		log.debug("FriendController ====> Starting of the sendFriendRequest method()");
		
		String loggedInUserId = (String) session.getAttribute("loggedInUserId");
		if(loggedInUserId==null)
		{
			friend.setErrorCode("404");
			friend.setErrorMessage("Please Login to Continue,.,");
			log.debug("FriendController ====> Ending of the sendFriendRequest method()");

		}
		else
		{
			friend.setId(friendDAO.maxID());
			friend.setStatus('N');
			friend.setEmailId(loggedInUserId);
			friend.setFriendEmailId(friendId);
			if(friendDAO.save(friend)==false)
			{
				friend.setErrorCode("404");
				friend.setErrorMessage("Error while adding friend ,.,please try again after sometime,.,!!,.,!!,.,");
				log.debug("FriendController ====> Ending of the sendFriendRequest method()");

			}
			else
			{
				friend.setErrorCode("200");
				friend.setErrorMessage("Friend Request has been sent");
				log.debug("FriendController ====> Ending of the sendFriendRequest method()");

			}
		}
		
		return new ResponseEntity<Friend>(friend,HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/acceptFriendRequest/{friendId}",method=RequestMethod.PUT)
	public ResponseEntity<Friend> acceptFriendRequest(@PathVariable("friendId") String friendEmailId)
	{
		log.debug("FriendController ====> Starting of the acceptFriendRequest method()");

		friend = acceptOrRejectFriendRequest(friendEmailId, 'A');
		
		log.debug("FriendController ====> Ending of the acceptFriendRequest method()");

		return new ResponseEntity<Friend>(friend,HttpStatus.OK);


	}

	
	@RequestMapping(value="/rejectFriendRequest/{friendId}",method=RequestMethod.PUT)
	public ResponseEntity<Friend> rejectFriendRequest(@PathVariable("friendId") String friendEmailId)
	{
		log.debug("FriendController ====> Starting of the rejectFriendRequest method()");

		friend = acceptOrRejectFriendRequest(friendEmailId, 'R');
		
		log.debug("FriendController ====> Ending of the rejectFriendRequest method()");

		return new ResponseEntity<Friend>(friend,HttpStatus.OK);

	}

	
	
	
	@RequestMapping("/deleteFriend/{friendId}")
	public ResponseEntity<Friend> deleteFriend(@PathVariable("friendId") String friendId)
	{
		Friend friend = (Friend) friendDAO.getMyFriendRequests(friendId);
		if(friendDAO.delete(friend)==false)
		{
			friend.setErrorCode("404");
			friend.setErrorMessage("Error while deleting friend ,.,please try again after sometime,.,!!,.,!!,.,");
		}
		else
		{
			friend.setErrorCode("200");
			friend.setErrorMessage("Friend has been deleted");
		}
		
		return new ResponseEntity<Friend>(friend,HttpStatus.OK);
		
	}
	
	private Friend acceptOrRejectFriendRequest(String friendEmailId,char status)
	{
		log.debug("FriendController ====> Starting of the acceptOrRejectFriendRequest method()");

		String loggedInUser = (String) session.getAttribute("loggedInUser");
		if(loggedInUser==null)
		{
			friend.setErrorCode("404");
			friend.setErrorMessage("Please Login to Continue,.,");
			log.debug("FriendController ====> Ending of the acceptOrRejectFriendRequest method()");

		}
		else
		{
			friend = friendDAO.get(friendEmailId, loggedInUser);
			friend.setStatus(status);
			friendDAO.update(friend);
			friend.setErrorCode("200");
			friend.setErrorMessage("Operation has been Successsful");
			log.debug("FriendController ====> Ending of the acceptOrRejectFriendRequest method()");

		}
		return friend;
	}
}
