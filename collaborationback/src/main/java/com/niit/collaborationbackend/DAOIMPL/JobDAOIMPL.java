package com.niit.collaborationbackend.DAOIMPL;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
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
	
	
	@Transactional
	public boolean saveJob(Job job) {
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
	
	@Transactional
	public boolean updateJob(Job job) {
		try {
			sessionFactory.getCurrentSession().update(job);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	
	@Transactional
	public Job getJob(String id) {
		return (Job) sessionFactory.getCurrentSession().get(Job.class, id);
	}

	
	/*@Transactional
	public List<Job> getOpenJobs() {
		String hql= "From Job";
		Query query= sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}
*/
	
	@Transactional
	public List<Job> getjobs(String username) {
		System.out.println("Calling getOpenJobs with "+username);
		SQLQuery query1=(SQLQuery) sessionFactory.getCurrentSession().createSQLQuery("select * from Job where Id <> (select jobId from Job_applied where username = ?)");

		query1.setString(0, username);

			/*List<Job> alljobs = query1.list();
			System.out.println("Ending getOpenJobs with "+username);
			System.out.println(alljobs);
*/

			return query1.list();
	}

	
	@Transactional
	public Integer maxID()
	{
		Integer maxId = 100;
		try {
			String hql = "Select max(id) from Job";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			maxId= (Integer) query.uniqueResult();
		} catch (HibernateException e) {
			maxId= 100;
			e.printStackTrace();
		}
		return maxId+1;
	}
	

}
