package ua.nure.selezen.anastasiia.kn156.db;

import java.util.Collection;

import ua.nure.selezen.anastasiia.kn156.User;

/**
 * Interface for User class which implement DAO pattern with all CRUD opps
 * 
 * @author Anastasiia
 *
 */
public interface UserDAO {
	/**
	 * Add user into DB users table and get new user`s id from db
	 * 
	 * @param user
	 *            all field of user must be non-null except of id field
	 * @return copy of user from db with id auto-created
	 * @throws DatabaseExeption
	 *             in case of any error with db
	 */
	User create(User user) throws DatabaseExeption;

	/**
	 * Find user into DB users table with specified user`s id from db
	 * 
	 * @param id
	 *            id field of user must be non-null
	 * @return copy of user from db with specified id
	 * @throws DatabaseExeption
	 *             in case of any error with db
	 */
	User find(Long id) throws DatabaseExeption;

	/**
	 * Update user into DB users table with specified user`s id from db
	 * 
	 * @param user
	 *            all field of user must be non-null
	 * @throws DatabaseExeption
	 *             in case of any error with db
	 */
	void update(User user) throws DatabaseExeption;

	/**
	 * Delete user into DB users table with specified user`s id from db
	 * 
	 * @param user
	 *            all field of user must be non-null
	 * @throws DatabaseExeption
	 *             in case of any error with db
	 */
	void delete(User user) throws DatabaseExeption;

	/**
	 * Find all users into DB users table
	 * 
	 * All field of user must be non-null
	 * 
	 * @return collection of rows from DB users table
	 * @throws DatabaseExeption
	 *             in case of any error with db
	 */
	Collection<User> findAll() throws DatabaseExeption;

	void setConnectionFactory(ConnectionFactory connectionFactory);
}