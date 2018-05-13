package se.codepatcher.taskmanager.query;

public final class UserSQLQuery {

	public static final String GET_USERS = "SELECT * FROM users LIMIT ?, ?";
	public static final String GET_USER_BY_NAME = "SELECT * FROM users WHERE first_name LIKE ? AND last_name LIKE ? AND user_name LIKE ?";
	public static final String GET_USER_BY_ID = "SELECT * FROM USERS WHERE ID = ?";
	public static final String INSERT_USER = "INSERT INTO users VALUES (NULL,?,?,?,TRUE,NULL)";
	public static final String UPDATE_USERNAME = "UPDATE USERS SET user_name = ? WHERE id = ?";
	public static final String UPDATE_FIRSTNAME = "UPDATE USERS SET first_name = ? WHERE id = ?";
	public static final String UPDATE_LASTNAME = "UPDATE USERS SET last_name = ? WHERE id = ?";
	public static final String UPDATE_STATUS = "UPDATE USERS SET active_user = ? WHERE id = ?";
}