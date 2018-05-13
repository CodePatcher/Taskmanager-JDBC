package se.codepatcher.taskmanager.query;

public final class IssueSQLQuery {

	public static final String INSERT_ISSUE = "INSERT INTO issue VALUES (NULL,?,?,?,?)";
	public static final String GET_ISSUES = "SELECT * FROM issue LIMIT ?, 10";
	public static final String GET_ISSUE_BY_ID = "SELECT * FROM issue WHERE id = ?";
	public static final String UPDATE_TITLE = "UPDATE issue SET title = ? WHERE id = ?";
	public static final String UPDATE_DESCRIPTION = "UPDATE issue SET description = ? WHERE id = ?";
	public static final String UPDATE_OPEN_ISSUE = "UPDATE issue SET open_issue = ? WHERE id = ?";
	
}