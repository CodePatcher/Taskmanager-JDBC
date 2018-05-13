package se.codepatcher.taskmanager.service;

import java.sql.SQLException;
import java.util.List;
import se.codepatcher.taskmanager.exception.RepositoryException;
import se.codepatcher.taskmanager.exception.ServiceException;
import se.codepatcher.taskmanager.model.Team;
import se.codepatcher.taskmanager.model.User;
import se.codepatcher.taskmanager.repository.TeamRepository;
import se.codepatcher.taskmanager.repository.TeamSQLRepository;

public final class TeamService {

	private TeamRepository teamRepository;

	public TeamService() {
		teamRepository = new TeamSQLRepository();
	}
	
	public List<Team> getAllTeams() throws RepositoryException, SQLException {
		return teamRepository.getAllTeams();
	}

	public Team addTeam(String teamName) throws RepositoryException, SQLException {
		return teamRepository.addTeam(teamName);
	}

	public int updateTeamName(String newName, int id) throws RepositoryException, SQLException {
		return teamRepository.updateTeamName(newName, id);
	}

	public int setStatus(int teamId, boolean activeTeam) throws RepositoryException, SQLException {
		return teamRepository.setStatus(teamId, activeTeam);
	}

	public int addUserToTeam(int teamId, int userId) throws RepositoryException, ServiceException, SQLException {
		if (teamRepository.getNumberOfUsersInTeam(teamId) < 10) {
			return teamRepository.addUserToTeam(teamId, userId);
		}
		throw new ServiceException("Can't add user, team is full");
	}
	
	public List<User> getAllUsersInTeam(int teamId) throws RepositoryException, SQLException {
		return teamRepository.getAllUsersInTeam(teamId);
	}

	public Team getTeamById(int id) throws RepositoryException, SQLException {
		return teamRepository.getTeamById(id);
	}
}