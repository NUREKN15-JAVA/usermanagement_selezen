package ua.nure.selezen.anastasiia.kn156.db;

import java.io.IOException;
import java.util.Properties;

public class DaoFactory {

	private static final String USER_DAO = "dao.ua.nure.selezen.anastasiia.kn156.db.UserDAO";
	private final Properties properties;

	private final static DaoFactory INSTANCE = new DaoFactory();

	public static DaoFactory getInstance() {
		return INSTANCE;
	}

	private DaoFactory() {
		properties = new Properties();
		try {
			properties.load(getClass().getClassLoader().getResourceAsStream("settings.properties"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private ConnectionFactory getConnectionFactory() {
		String user = properties.getProperty("connection.user");
		String password = properties.getProperty("connection.password");
		String url = properties.getProperty("connection.url");
		String driver = properties.getProperty("connection.driver");

		return new ConnectionFactoryImpl(driver, url, user, password);
	}

	public UserDAO getUserDao() {
		UserDAO result = null;
		try {
			Class clazz = Class.forName(properties.getProperty(USER_DAO));
			result = (UserDAO) clazz.newInstance();
		} catch (ReflectiveOperationException e) {
			throw new RuntimeException(e);
		}
		result.setConnectionFactory(getConnectionFactory());
		return result;
	}
}