package com.niit.collaborationbackend.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.collaborationbackend.model.Blog;
import com.niit.collaborationbackend.model.BlogComments;
import com.niit.collaborationbackend.model.Friend;
import com.niit.collaborationbackend.model.Job;
import com.niit.collaborationbackend.model.JobApplication;
import com.niit.collaborationbackend.model.User;

@Configuration
@ComponentScan("com.niit")
@EnableTransactionManagement
public class ApplicationContextConfig {
	
	@Bean(name="dataSource")	
	public DataSource getDataSource(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		
		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		
		dataSource.setUsername("CollaborationConnectMe");
		dataSource.setPassword("Ankur");
		
		Properties getconnectionproperties = new Properties();
		getconnectionproperties.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");

		dataSource.setConnectionProperties(getconnectionproperties);
	
		/*	dataSource.setUrl("jdbc:h2:tcp://localhost/~/test");
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUsername("sa");
		dataSource.setPassword("");
	*/
		
		return (DataSource) dataSource;
	}
	/*
private Properties getHibernateProperties (){
		
		Properties properties = new Properties();
		properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		return properties;
	}
	*/
	
	@Autowired
	@Bean(name="sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource){
		
		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
/*	sessionBuilder.addAnnotatedClass(Category.class);
	sessionBuilder.addAnnotatedClass(Supplier.class);
	sessionBuilder.addAnnotatedClass(Product.class);
	sessionBuilder.addAnnotatedClass(Order.class);
	sessionBuilder.addAnnotatedClass(User.class);
	sessionBuilder.addAnnotatedClass(BillingAddress.class);
	sessionBuilder.addAnnotatedClass(ShippingAddress.class);
	sessionBuilder.addAnnotatedClass(Contactus.class);

sessionBuilder.addAnnotatedClass(Cart.class);
*/	//sessionBuilder.addProperties(getHibernateProperties());
		sessionBuilder.addAnnotatedClass(User.class);
		sessionBuilder.addAnnotatedClass(Blog.class);
		sessionBuilder.addAnnotatedClass(Job.class);
		sessionBuilder.addAnnotatedClass(JobApplication.class);
		sessionBuilder.addAnnotatedClass(Friend.class);
		sessionBuilder.addAnnotatedClass(BlogComments.class);

return sessionBuilder.buildSessionFactory();
	}
	

	
	
	
		@Autowired
	@Bean(name="transactionManager")
	public HibernateTransactionManager getTransactionalManager(SessionFactory sessionFactory){
		
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
		return transactionManager;
	}
	

}
