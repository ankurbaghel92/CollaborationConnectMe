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
		String hql = "From Friend where emaild= '"+emailId+"' and status ='N'";
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
		String hql = "From Friend where emailId = '"+ emailId + "' and status ='A'"
				+ "UNION +"
				+ "From Friend Where emailId = '" +emailId+ "' and status = 'A'"
						+ "MINUS"
						+ "From Friend where emaild = '"+emailId+"'";
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<Friend> myFriends = query.list();
		return myFriends;
	}
	
	@Transactional
	public Integer maxID()
	{
		Integer maxId = 100;
		String hql = "Select max(id) from JobApplication";
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
