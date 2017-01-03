package com.niit.collaborationbackend.DAOIMPL;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.collaborationbackend.DAO.JobApplicationDAO;
import com.niit.collaborationbackend.model.JobApplication;

@Repository("jobApplicationDAO")
public class JobApplicationDAOIMPL implements JobApplicationDAO {
	
	@Autowired
	SessionFactory sessionFactory;
	public JobApplicationDAOIMPL(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}

	public JobApplication get(String Id) {
		return (JobApplication) sessionFactory.getCurrentSession().get(JobApplication.class, Id);
	}

	public List<JobApplication> list() {
		String hql= "From JobApplication";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	public boolean save(JobApplication jobApplication) {
		try {
			sessionFactory.getCurrentSession().save(jobApplication);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		
	}

	public boolean update(JobApplication jobApplication) {
		try {
			sessionFactory.getCurrentSession().update(jobApplication);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

}