package ua.nure.selezen.anastasiia.kn156;

import static org.junit.Assert.*;
import java.util.Calendar;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import junit.framework.TestCase;

public class UserTest extends TestCase {
	private User user;
	private Date dateOfBirthday;
	private static final int AGE = 19;
	private static final int YEAR = 1998;
	private static final int DAY = 29;

	@Before
	public void setUp() throws Exception {
		super.setUp();
		user = new User();
		Calendar calendar = Calendar.getInstance();
		calendar.set(YEAR, Calendar.JULY, DAY);
		dateOfBirthday = calendar.getTime();
		
	}

	@Test
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
