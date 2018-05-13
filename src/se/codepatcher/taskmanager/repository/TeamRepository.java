package se.codepatcher.taskmanager.repository;

import java.sql.SQLException;
import java.util.List;

import se.codepatcher.taskmanager.exception.RepositoryException;
import se.codepatcher.taskmanager.model.Team;
import se.codepatcher.taskmanager.model.User;


public interface TeamRepository {

	List<Team> getAllTeams() throws RepositoryException, SQLException;

	Team addTeam(String teamName) throws RepositoryException, SQLException;

	int updateTeamName(String newName, int id) throws RepositoryException, SQLException;

	int setStatus(int teamId, boolean activeTeam) throws RepositoryException, SQLException;

	int addUserToTeam(int teamId, int userId) throws RepositoryException, SQLException;

	List<User> getAllUsersInTeam(int teamId) throws RepositoryException, SQLException;

	Team getTeamById(int id) throws RepositoryException, SQLException;

	int getNumberOfUsersInTeam(int teamId) throws RepositoryException, SQLException;
}