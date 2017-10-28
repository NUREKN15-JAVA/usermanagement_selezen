package ua.nure.selezen.anastasiia.kn156;

import java.util.Calendar;
import java.util.Date;
import junit.framework.TestCase;

public class UserTest extends TestCase {
	private User user;
	private Date dateOfBirthday;
	/**
	 * Constant AGE indicate the age as of 29.10.17 
	 */
	private static final int AGE = 19;
	private static final int YEAR = 1998;
	private static final int DAY = 29;

	
	public void setUp() throws Exception {
		super.setUp();
		user = new User();
		Calendar calendar = Calendar.getInstance();
		calendar.set(YEAR, Calendar.JULY, DAY);
		dateOfBirthday = calendar.getTime();
		
	}

	
	public void testGetFullName() {
		user.setFirstName("Anastasiia");
		user.setLastName("Selezen");
		assertEquals("Selezen, Anastasiia", user.getFullName());
	}
	
	public void testGetAge(){
		user.setDateOfBirthday(dateOfBirthday);
		assertEquals(AGE, user.getAge());
	}
}
