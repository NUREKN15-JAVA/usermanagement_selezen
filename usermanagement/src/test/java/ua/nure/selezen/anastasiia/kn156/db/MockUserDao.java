package ua.nure.selezen.anastasiia.kn156.db;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import ua.nure.selezen.anastasiia.kn156.User;

public class MockUserDao implements UserDAO {

	private long id = 0;
	private Map<Long, User> users = new HashMap<>();

	@Override
	public User create(User user) throws DatabaseExeption {
		Long currentId = new Long(++id);
		user.setId(currentId);
		users.put(currentId, user);
		return user;
	}

	@Override
	public User find(Long id) throws DatabaseExeption {

		return users.get(id);
	}

	@Override
	public void update(User user) throws DatabaseExeption {
		Long currentId = user.getId();
		users.remove(currentId);
		users.put(currentId, user);

	}

	@Override
	public void delete(User user) throws DatabaseExeption {
		Long currentId = user.getId();
		users.remove(currentId);
	}

	@Override
	public Collection<User> findAll() throws DatabaseExeption {

		return users.values();
	}

	@Override
	public void setConnectionFactory(ConnectionFactory connectionFactory) {
		// TODO Auto-generated method stub

	}

	
	public Collection find(String firstName, String lastName) throws DatabaseExeption {
        throw new UnsupportedOperationException();
    }
}