package ua.nure.selezen.anastasiia.kn156.db;

import junit.framework.TestCase;

public class DaoFactoryTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testGetUserDao() {

		try {
			DaoFactory daoFactory = DaoFactory.getInstance();
			assertNotNull("DaoFactory instanse is null", daoFactory);
			UserDAO userDao = daoFactory.getUserDao();
			assertNotNull("UserDao instanse is null", userDao);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}

}
