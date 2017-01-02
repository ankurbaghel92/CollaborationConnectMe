package com.niit.collaborationbackend.DAOIMPL;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.collaborationbackend.DAO.JobDAO;
import com.niit.collaborationbackend.model.Job;

@Repository("jobDAO")
public class JobDAOIMPL implements JobDAO {
	
	@Autowired
	SessionFactory sessionFactory;
	
	public JobDAOIMPL(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}
	
	public boolean save(Job job) {
	try {
		sessionFactory.getCurrentSession().save(job);
		return true;
	} catch (HibernateException e) {
		e.printStackTrace();
		return false;
	}
	}

	public boolean delete(Job job) {
		try {
			sessionFactory.getCurrentSession().delete(job);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean update(Job job) {
		try {
			sessionFactory.getCurrentSession().update(job);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	public Job get(String id) {
		return (Job) sessionFactory.getCurrentSession().get(Job.class, id);
	}

	public List<Job> list() {
		String hql= "From Job";
		Query query= sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}
	
	

}
