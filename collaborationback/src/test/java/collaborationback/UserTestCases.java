package collaborationback;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.collaborationbackend.DAO.UserDAO;
import com.niit.collaborationbackend.model.User;

import junit.framework.Assert;

public class UserTestCases {

	@Autowired
	static User user;
	
	@Autowired
	static UserDAO userDAO;
	
	@Autowired
	static AnnotationConfigApplicationContext context;
	
	
	@BeforeClass
	public static void init()
	{
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		userDAO = (UserDAO) context.getBean("userDAO");
		user= (User) context.getBean("user");
	}
	
	//@Test
	public void CreateUserTestCase(){
		user.setEmailId("ankur.baghel92@gmail.com");
		user.setFname("Ankur");
		user.setLname("Baghel");
		user.setGender("Male");
		user.setMobile("8796375371");
		user.setPassword("Ankurb92");
		user.setRole("Admin");
		user.setReason("No Reason");
		user.setIsOnline('N');
		user.setStatus("N");
		boolean status = userDAO.save(user);
		
		Assert.assertEquals("CreateUserTestCase", true, status);
		
	}
	
	//@Test
	public void GetuserTestCase() {
		User user = userDAO.get("ankur.baghel92@gmail.com");
		System.out.println(user.getPassword());
		Assert.assertEquals("Get One user Test Case", null, userDAO.get("ankur.baghel92@gmail.com"));
	}

	
	@Test
	public void ListuserTestCase() {
		Assert.assertEquals("Get One user Test Case", null, userDAO.list());
	}
	
	
	/*@Test
	public void test() {
		fail("Not yet implemented");
	}
*/
}