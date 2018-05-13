package se.codepatcher.taskmanager.repository;

import java.sql.SQLException;
import java.util.List;
import se.codepatcher.taskmanager.exception.RepositoryException;
import se.codepatcher.taskmanager.model.User;

public interface UserRepository {
	
	User getUserByID(int id) throws RepositoryException, SQLException;

	List<User> getUserByName(String firstName, String lastName, String userName) throws RepositoryException, SQLException;
	
	List<User> getAllUsers(int page) throws RepositoryException, SQLException; 

	List<User> getAllUsers(int page, int size) throws RepositoryException, SQLException; 
	
	User addUser(String firstName, String lastName, String userName) throws RepositoryException, SQLException;

	int updateUser(int userId, String column, String newValue ) throws RepositoryException, SQLException ;

}