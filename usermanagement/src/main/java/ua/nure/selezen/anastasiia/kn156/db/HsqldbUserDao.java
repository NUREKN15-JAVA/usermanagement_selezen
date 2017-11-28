package ua.nure.selezen.anastasiia.kn156.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;

import ua.nure.selezen.anastasiia.kn156.User;

class HsqldbUserDao implements UserDAO {

	private static final String SELECT_ALL_QUERY = "SELECT id, firstname, lastname, dateofbirth FROM users";
	private static final String INSERT_QUERY = "INSERT INTO users (firstname, lastname, dateofbirth) VALUES (?, ?, ?)";
	private static final String DELETE_QUERY = "DELETE FROM users WHERE id = ?";
	private static final String UPDATE_QUERY = "UPDATE users SET firstname = ?, lastname = ?, dateofbirth = ? WHERE id = ?";
	private static final String FIND_QUERY = "SELECT id, firstname, lastname, dateofbirth FROM users WHERE id = ?";
	private static final String SELECT_BY_NAMES = "SELECT id, firstname, lastname, dateofbirth FROM users WHERE firstname = ? AND lastname = ?";

	private ConnectionFactory connectionFactory;

	public HsqldbUserDao() {
	}

	public HsqldbUserDao(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}

	public ConnectionFactory getConnectionFactory() {
		return connectionFactory;
	}

	public void setConnectionFactory(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}

	@Override
	public User create(User user) throws DatabaseExeption {
		try {
			Connection connection = connectionFactory.createConnection();
			PreparedStatement statment = connection.prepareStatement(INSERT_QUERY);

			statment.setString(1, user.getFirstName());
			statment.setString(2, user.getLastName());
			statment.setDate(3, new Date(user.getDateOfBirthday().getTime()));
			int n = statment.executeUpdate();
			if (n != 1) {
				throw new DatabaseExeption("Number of inserted rows:" + n);
			}

			CallableStatement callableStatement = connection.prepareCall("call IDENTITY()");
			ResultSet keys = callableStatement.executeQuery();
			User insertedUser = new User(user);
			if (keys.next()) {
				insertedUser.setId(new Long(keys.getLong(1)));
			}

			keys.close();
			callableStatement.close();
			statment.close();
			connection.close();
			return insertedUser;

		} catch (DatabaseExeption e) {
			throw e;
		} catch (SQLException e) {
			throw new DatabaseExeption(e);
		}

	}

	@Override
	public User find(Long id) throws DatabaseExeption {
		User user = null;
		try {
			Connection connection = connectionFactory.createConnection();
			PreparedStatement statement = connection.prepareStatement(FIND_QUERY);
			statement.setLong(1, id);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				user = new User();
				user.setId(new Long(resultSet.getLong(1)));
				user.setFirstName(resultSet.getString(2));
				user.setLastName(resultSet.getString(3));
				user.setDateOfBirthday(resultSet.getDate(4));
			}

			resultSet.close();
			statement.close();
			connection.close();

		} catch (SQLException e) {
			throw new DatabaseExeption(e);
		}
		return user;
	}

	@Override
	public void update(User user) throws DatabaseExeption {
		try {
			Connection connection = connectionFactory.createConnection();
			PreparedStatement statment = connection.prepareStatement(UPDATE_QUERY);

			statment.setString(1, user.getFirstName());
			statment.setString(2, user.getLastName());
			statment.setDate(3, new Date(user.getDateOfBirthday().getTime()));
			statment.setLong(4, user.getId());
			int n = statment.executeUpdate();
			if (n != 1) {
				throw new DatabaseExeption("Number of updated rows:" + n);
			}

			statment.close();
			connection.close();

		} catch (DatabaseExeption e) {
			throw e;
		} catch (SQLException e) {
			throw new DatabaseExeption(e);
		}

	}

	@Override
	public void delete(User user) throws DatabaseExeption {

		try {
			Connection connection = connectionFactory.createConnection();
			PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);

			statement.setLong(1, user.getId());

			int n = statement.executeUpdate();
			if (n != 1) {
				throw new DatabaseExeption("Number of the deleted rows: " + n);
			}
			statement.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Collection findAll() throws DatabaseExeption {
		Collection result = new LinkedList();

		try {
			Connection connection = connectionFactory.createConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(SELECT_ALL_QUERY);
			while (resultSet.next()) {
				User user = new User();
				user.setId(new Long(resultSet.getLong(1)));
				user.setFirstName(resultSet.getString(2));
				user.setLastName(resultSet.getString(3));
				user.setDateOfBirthday(resultSet.getDate(4));
				result.add(user);

			}
		} catch (DatabaseExeption e) {
			throw e;
		} catch (SQLException e) {
			throw new DatabaseExeption(e);
		}
		return result;
	}

	@Override
	public Collection find(String firstName, String lastName) throws DatabaseExeption {
		Collection result = new LinkedList();

		try {
			Connection connection = connectionFactory.createConnection();
			PreparedStatement statement = connection.prepareStatement(SELECT_BY_NAMES);
			statement.setString(1, firstName);
			statement.setString(2, lastName);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				User user = new User();
				user.setId(new Long(resultSet.getLong(1)));
				user.setFirstName(resultSet.getString(2));
				user.setLastName(resultSet.getString(3));
				user.setDateOfBirthday(resultSet.getDate(4));
				result.add(user);

			}
		} catch (DatabaseExeption e) {
			throw e;
		} catch (SQLException e) {
			throw new DatabaseExeption(e);
		}
		return result;
	}

}
