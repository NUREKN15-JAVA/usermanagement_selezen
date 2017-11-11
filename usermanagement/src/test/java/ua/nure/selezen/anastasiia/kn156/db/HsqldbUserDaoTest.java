package ua.nure.selezen.anastasiia.kn156.db;

import java.util.Collection;
import java.util.Date;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;

import ua.nure.selezen.anastasiia.kn156.User;

public class HsqldbUserDaoTest extends DatabaseTestCase {

	private static final String FIRST_NAME_UPDATE = "Anasta";
	private static final String LAST_NAME = "Doe";
	private static final String FIRST_NAME = "John";
	private static final String JDBC_DRIVER = "org.hsqldb.jdbcDriver";
	private static final String URL = "jdbc:hsqldb:file:db/usermanagement";
	private static final String USER = "sa";
	private static final String PASSWORD = "";
	private static final int NUMBER_OF_ROWS = 3;
	private static final Long ID = 1000L;

	private HsqldbUserDao dao;
	private ConnectionFactory connectionFactory;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		dao = new HsqldbUserDao(connectionFactory);

	}

	public void testCreate() {

		try {
			User user = new User();
			user.setFirstName(FIRST_NAME);
			user.setLastName(LAST_NAME);
			user.setDateOfBirthday(new Date());
			assertNull(user.getId());
			User createdUser;
			createdUser = dao.create(user);
			assertNotNull(createdUser);
			assertNotNull(createdUser.getId());
			assertEquals(user.getFullName(), createdUser.getFullName());
			assertEquals(user.getAge(), createdUser.getAge());
		} catch (DatabaseExeption e) {
			fail(e.toString());
		}
	}

	public void testFind() {
		try {
			User user = dao.find(ID);
			assertNotNull(user);
			assertEquals("Search user by id", ID, user.getId());
		} catch (DatabaseExeption e) {
			fail(e.toString());
		}
	}

	public void testUpdate() {
		try {
			User user = dao.find(ID);
			assertNotNull(user);
			user.setFirstName(FIRST_NAME_UPDATE);
			dao.update(user);
			User updatedUser = dao.find(ID);
			assertEquals(user.getFirstName(), updatedUser.getFirstName());
		} catch (DatabaseExeption e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}

	public void testDelete() {
		User user = null;
		try {
			user = dao.find(ID);
			assertNotNull(user);
			dao.delete(user);
			User deletedUser = dao.find(ID);
			assertNull(deletedUser);
		} catch (DatabaseExeption e) {
			fail(e.toString());
		}
	}

	public void testFindAll() {
		try {
			Collection collection = dao.findAll();
			assertNotNull("Collection is null", collection);
			assertEquals("Collection size.", NUMBER_OF_ROWS, collection.size());
		} catch (DatabaseExeption e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Override
	protected IDatabaseConnection getConnection() throws Exception {
		connectionFactory = new ConnectionFactoryImpl(JDBC_DRIVER, URL, USER, PASSWORD);
		return new DatabaseConnection(connectionFactory.createConnection());
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		IDataSet dataSet = new XmlDataSet(getClass().getClassLoader().getResourceAsStream("usersDataSet.xml"));
		return dataSet;
	}

}