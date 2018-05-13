package se.codepatcher.taskmanager.repository;

import java.sql.SQLException;
import java.util.List;
import se.codepatcher.taskmanager.exception.RepositoryException;
import se.codepatcher.taskmanager.model.User;
import se.codepatcher.taskmanager.query.UserSQLQuery;

public final class UserSQLRepository implements UserRepository {
	
	@Override
	public User getUserByID(int id) throws RepositoryException, SQLException {
		String query = UserSQLQuery.GET_USER_BY_ID;
		
		List<User> userList = getHelper(query).addParameter(id).executeQuery(r -> new User(r.getInt(1), r.getString(2),
				r.getString(3), r.getString(4), r.getBoolean(5), r.getInt(6)));
		
		return userList.get(0);
	}
	
	@Override
	public List<User> getAllUsers(int page) throws RepositoryException, SQLException {
		return getAllUsers(page, 10);}
	
	@Override
	public List<User> getAllUsers(int page,int size) throws RepositoryException, SQLException {
		String query = UserSQLQuery.GET_USERS;
		
		List<User> userList = getHelper(query).addParameter(page).addParameter(size).executeQuery
				(r -> new User(r.getInt(1), r.getString(2), r.getString(3),r.getString(4),
				 r.getBoolean(5), r.getInt(6)));
		
		return userList;
	}

	@Override
	public List<User> getUserByName(String firstName, String lastName, String userName) throws RepositoryException, SQLException {
		String query = UserSQLQuery.GET_USER_BY_NAME;
		
		if (firstName.length() == 0) {
			firstName = "%";
		}
		if (lastName.length() == 0) {
			lastName = "%";
		}
		if (userName.length() == 0) {
			userName = "%";
		}

		List<User> userList = getHelper(query).addParameter(firstName).addParameter(lastName).addParameter(userName)
				.executeQuery(r -> new User(r.getInt(1), r.getString(2), r.getString(3), r.getString(4),
						r.getBoolean(5), r.getInt(6)));

		return userList;
	}
	
	@Override
	public User addUser(String firstName, String lastName, String userName) throws RepositoryException, SQLException {
		String query = UserSQLQuery.INSERT_USER;
		
		List<Integer> genereatedId = getHelper(query).addParameter(firstName).addParameter(lastName)
				.addParameter(userName).executeUpdate(r -> r.getInt(1));
		
		return new User(genereatedId.get(0), firstName, lastName, userName, true, 0);
	}

	@Override
	public int updateUser(int userId, String column, String newValue) throws RepositoryException, SQLException {
		String query = null;
		int rowsAffected = 0;

		switch (column) {
		case "firstName":
			query = UserSQLQuery.UPDATE_FIRSTNAME;
			rowsAffected = getHelper(query).addParameter(newValue).addParameter(userId).executeUpdate();
			return rowsAffected;
		case "lastName":
			query = UserSQLQuery.UPDATE_LASTNAME;
			rowsAffected = getHelper(query).addParameter(newValue).addParameter(userId).executeUpdate();
			return rowsAffected;
		case "userName":
			query = UserSQLQuery.UPDATE_USERNAME;
			rowsAffected = getHelper(query).addParameter(newValue).addParameter(userId).executeUpdate();
			return rowsAffected;
		case "activeUser":
			boolean state;
			if (newValue.equalsIgnoreCase("true")) {
				state = true;
			} else if (newValue.equalsIgnoreCase("false")) {
				state = false;
			} else {
				throw new RepositoryException("invalid update status type");
			}
			query = UserSQLQuery.UPDATE_STATUS;
			rowsAffected = getHelper(query).addParameter(state).addParameter(userId).executeUpdate();
			return rowsAffected;
		default:
			break;
		}
		return rowsAffected;
	}

	private SQLHelper getHelper(String query) throws SQLException {
		return new SQLHelper(query);
	}
}