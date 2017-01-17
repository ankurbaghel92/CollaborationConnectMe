package com.niit.collaborationbackend.DAOIMPL;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.collaborationbackend.DAO.FriendDAO;
import com.niit.collaborationbackend.model.Friend;

@Repository("friendDAO")
public class FriendDAOIMPL implements FriendDAO {
	
	@Autowired
	SessionFactory sessionFactory;
	
	public FriendDAOIMPL(SessionFactory sessionFactory)
	{
		this.sessionFactory =sessionFactory;
	}
	

	@Transactional
	public boolean save(Friend friend) {
		try {
			sessionFactory.getCurrentSession().save(friend);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean delete(Friend friend) {
		try {
			sessionFactory.getCurrentSession().delete(friend);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	public List<Friend> getMyFriendRequests(String emailId) {
		String hql = "select emailId From Friend where friendEmailId= '"+emailId+"' and status ='N'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}
	
	
	@Transactional
	public List<Friend> getMySentFriendRequest(String emailId) {
		String hql = "select friendEmailId From Friend where emailId= '"+emailId+"' and status ='N'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}
	
	
	@Transactional
	public void setOnline(String emailId)
	{
		String hql = "UPDATE Friend SET isOnline = 'Y' where emailId= '"+emailId+"'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}
	
	
	@Transactional
	public void setOffline(String emailId)
	{
		String hql = "UPDATE Friend SET isOnline = 'N' where emailId= '"+emailId+"'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}

	
	
	@Transactional
	public Friend get(String emailId, String friendEmailId) {
		String hql = "From Friend where emailId = '" +emailId+ "' and friendEmailId = '" +friendEmailId+ "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return (Friend) query.uniqueResult();
	}


	@Transactional
	public boolean update(Friend friend) {
		try {
			sessionFactory.getCurrentSession().update(friend);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}


	@Transactional
	public List<Friend> getMyFriends(String emailId) {
		String hql1 = "Select friendEmailId from Friend where emailId = '"+ emailId + "' and status ='A'";
			//	+ "UNION +"
				String hql2 = "Select emailId From Friend Where friendEmailId = '" +emailId+ "' and status = 'A'";
						//+ "MINUS"
						//+ "From Friend where emaild = '"+emailId+"'";
		
		Query query1 = sessionFactory.getCurrentSession().createQuery(hql1);
		Query query2 = sessionFactory.getCurrentSession().createQuery(hql2);

		List<Friend> myFriends1 = query1.list();
		List<Friend> myFriends2 = query2.list();

		myFriends1.addAll(myFriends2);
		return myFriends1;
	}
	
	@Transactional
	public Integer maxID()
	{
		Integer maxId = 100;
		String hql = "Select max(id) from Friend";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		try {
			maxId= (Integer) query.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
			return maxId;
		}
		return maxId+1;
	}

}
