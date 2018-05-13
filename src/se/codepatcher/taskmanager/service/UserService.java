package se.codepatcher.taskmanager.service;

import java.sql.SQLException;
import java.util.List;
import se.codepatcher.taskmanager.exception.RepositoryException;
import se.codepatcher.taskmanager.exception.ServiceException;
import se.codepatcher.taskmanager.model.User;
import se.codepatcher.taskmanager.repository.UserRepository;
import se.codepatcher.taskmanager.repository.UserSQLRepository;
import se.codepatcher.taskmanager.repository.WorkItemRepository;
import se.codepatcher.taskmanager.repository.WorkItemSQLRepository;

public final class UserService {

	private final UserRepository userRepository;
	private final WorkItemRepository workItemRepository;

	public UserService() {
		userRepository = new UserSQLRepository();
		workItemRepository = new WorkItemSQLRepository();
	}

	public User getUserByID(int id) throws RepositoryException, SQLException {
		return userRepository.getUserByID(id);
	}

	public List<User> getAllUsers(int page) throws RepositoryException, ServiceException, SQLException {
		if(page < 0) {
			throw new ServiceException("paging is only allowed with a value bigger or equal to zero");
		}
		return userRepository.getAllUsers(page);
	}

	public List<User> getUserByName(String firstName, String lastName, String userName) throws RepositoryException, SQLException {
		return userRepository.getUserByName(firstName, lastName, userName);
	}

	public User addUser(String firstName, String lastName, String userName)
			throws RepositoryException, ServiceException, SQLException {
		if (isValidUsername(userName)) {
			return userRepository.addUser(firstName, lastName, userName);
		}
		throw new ServiceException("username too short");
	}

	public int updateUser(int userId, String column, String newValue) throws RepositoryException, ServiceException, SQLException {
		switch (column) {
		case "userName":
			if (isValidUsername(newValue)) {
				return userRepository.updateUser(userId, column, newValue);
			}
			throw new ServiceException("username too short");

		case "firstName":
			return userRepository.updateUser(userId, column, newValue);

		case "lastName":
			return userRepository.updateUser(userId, column, newValue);

		case "activeUser":
			if (newValue.equalsIgnoreCase("false")) {
				workItemRepository.changeStatusByUserId(userId, "Unstarted");
			}
			return userRepository.updateUser(userId, column, newValue);

		default:
			throw new ServiceException("Invalid update command: " + column);
		}
	}

	private boolean isValidUsername(String username) {
		if (username.length() >= 10) {
			return true;
		}
		return false;
	}

}