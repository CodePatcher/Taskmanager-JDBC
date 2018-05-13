package se.codepatcher.taskmanager.repository;

import java.sql.SQLException;
import java.util.List;
import se.codepatcher.taskmanager.exception.RepositoryException;
import se.codepatcher.taskmanager.model.Team;
import se.codepatcher.taskmanager.model.User;
import se.codepatcher.taskmanager.query.TeamSQLQuery;

public final class TeamSQLRepository implements TeamRepository {

	@Override
	public List<Team> getAllTeams() throws RepositoryException, SQLException {
		String query = TeamSQLQuery.GET_TEAMS;

		List<Team> listOfTeams = getHelper(query)
				.executeQuery(r -> new Team(r.getInt(1), r.getString(2), r.getInt(3), r.getBoolean(4)));

		return listOfTeams;
	}

	@Override
	public List<User> getAllUsersInTeam(int teamId) throws RepositoryException, SQLException {
		String query = TeamSQLQuery.GET_ALL_USERS_IN_TEAM;

		List<User> listOfUsers = getHelper(query).addParameter(teamId).executeQuery(r -> new User(r.getInt(1),
				r.getString(2), r.getString(3), r.getString(4), r.getBoolean(5), r.getInt(6)));

		return listOfUsers;
	}

	@Override
	public Team addTeam(String teamName) throws RepositoryException, SQLException {
		String query = TeamSQLQuery.ADD_TEAM;

		List<Integer> generatedId = getHelper(query).addParameter(teamName).executeUpdate(r -> r.getInt(1));

		return new Team(generatedId.get(0), teamName, 0, true);
	}

	@Override
	public int updateTeamName(String newName, int id) throws RepositoryException, SQLException {
		String query = TeamSQLQuery.UPDATE_TEAM;

		int rowsAffected = getHelper(query).addParameter(newName).addParameter(id).executeUpdate();

		return rowsAffected;
	}

	@Override
	public int setStatus(int teamId, boolean activeTeam) throws RepositoryException, SQLException {
		String query = TeamSQLQuery.SET_STATUS;

		int rowsAffected = getHelper(query).addParameter(activeTeam).addParameter(teamId).executeUpdate();

		return rowsAffected;
	}

	@Override
	public int addUserToTeam(int teamId, int userId) throws RepositoryException, SQLException {
		String query = TeamSQLQuery.ADD_USER_TO_TEAM;
		String updatingSQLTable = TeamSQLQuery.UPDATE_NUMBER_OF_MEMBERS_IN_TEAM; 

		int rowsAffected = getHelper(query).addParameter(teamId).addParameter(userId).executeUpdate();
		getHelper(updatingSQLTable).executeUpdate(); 

		return rowsAffected;
	}

	@Override
	public Team getTeamById(int id) throws RepositoryException, SQLException {
		String query = TeamSQLQuery.GET_TEAM_BY_ID;

		List<Team> teamList = getHelper(query).addParameter(id)
				.executeQuery(r -> new Team(r.getInt(1), r.getString(2), r.getInt(3), r.getBoolean(4)));

		return teamList.get(0);
	}

	@Override
	public int getNumberOfUsersInTeam(int teamId) throws RepositoryException, SQLException {
		String query = TeamSQLQuery.GET_NUMBER_OF_USERS_IN_TEAM;
		List<Integer> teamSize = getHelper(query).addParameter(teamId).executeQuery(r -> r.getInt(1));

		return teamSize.get(0);
	}

	private SQLHelper getHelper(String query) throws SQLException {
		return new SQLHelper(query);
	}

}