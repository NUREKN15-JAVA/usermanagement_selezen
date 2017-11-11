package ua.nure.selezen.anastasiia.kn156.db;

import java.io.IOException;
import java.util.Properties;

import javax.management.RuntimeErrorException;

public abstract class DaoFactory {

	protected static final String USER_DAO = "dao.ua.nure.selezen.anastasiia.kn156.db.UserDAO";
	protected static Properties properties;
	private static final String DAO_FACTORY = "dao.factory";

	private static DaoFactory INSTANCE;

	static {
		properties = new Properties();
		try {
			properties.load(DaoFactory.class.getClassLoader().getResourceAsStream("settings.properties"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static synchronized DaoFactory getInstance() {
		if (INSTANCE == null) {
			Class factoryClass;
			try {
				factoryClass = Class.forName(properties.getProperty(DAO_FACTORY));
				INSTANCE = (DaoFactory) factoryClass.newInstance();

			} catch (Exception e) {
				throw new RuntimeException(e);

			}
		}
		return INSTANCE;
	}

	protected DaoFactory() {

	}

	/*
	 * private DaoFactory() { properties = new Properties(); try {
	 * properties.load(getClass().getClassLoader().getResourceAsStream(
	 * "settings.properties")); } catch (IOException e) { throw new
	 * RuntimeException(e); } }
	 */
	/*
	 * protected ConnectionFactory getConnectionFactory() { String user =
	 * properties.getProperty("connection.user"); String password =
	 * properties.getProperty("connection.password"); String url =
	 * properties.getProperty("connection.url"); String driver =
	 * properties.getProperty("connection.driver");
	 * 
	 * return new ConnectionFactoryImpl(driver, url, user, password); }
	 */
	public static void init(Properties prop) {
		properties = prop;
		INSTANCE = null;
	}

	protected ConnectionFactory getConnectionFactory() {
		return new ConnectionFactoryImpl(properties);
	}

	public abstract UserDAO getUserDao();
	/*
	 * { UserDAO result = null; try { Class clazz =
	 * Class.forName(properties.getProperty(USER_DAO)); result = (UserDAO)
	 * clazz.newInstance(); } catch (ReflectiveOperationException e) { throw new
	 * RuntimeException(e); }
	 * result.setConnectionFactory(getConnectionFactory()); return result; } }
	 */

}