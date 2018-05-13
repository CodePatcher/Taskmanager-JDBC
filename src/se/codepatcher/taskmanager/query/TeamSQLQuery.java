package se.codepatcher.taskmanager.query;

public final class TeamSQLQuery {

	public static final String GET_TEAMS = "SELECT * FROM team";
	public static final String ADD_TEAM = "INSERT INTO team VALUES (null, ?, 0, true)";
	public static final String UPDATE_TEAM = "UPDATE team SET team_name = ? WHERE id = ?";
	public static final String SET_STATUS = "UPDATE team SET active_team = ? WHERE id = ?";
	public static final String ADD_USER_TO_TEAM = "UPDATE users SET team_id = ? WHERE id = ?";
	public static final String GET_ALL_USERS_IN_TEAM = "SELECT * FROM users WHERE users.team_id = ?";
	public static final String GET_TEAM_BY_ID = "SELECT * FROM team WHERE id = ?";
	public static final String GET_NUMBER_OF_USERS_IN_TEAM = "SELECT COUNT(*) FROM users WHERE team_id = ?";
	public static final String UPDATE_NUMBER_OF_MEMBERS_IN_TEAM = "UPDATE `team`  SET number_of_members = "
							+ "(SELECT COUNT(id) FROM `users` WHERE users.team_id = team.id )"; 
}