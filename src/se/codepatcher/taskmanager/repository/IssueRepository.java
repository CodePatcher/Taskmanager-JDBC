package se.codepatcher.taskmanager.repository;

import java.sql.SQLException;
import java.util.List;
import se.codepatcher.taskmanager.exception.RepositoryException;
import se.codepatcher.taskmanager.model.Issue;


public interface IssueRepository {

	Issue getIssueById(int id) throws RepositoryException, SQLException;
	
	List<Issue> getAllIssues(int page) throws RepositoryException, SQLException;
	
	List<Issue> getAllIssues(int page, int size) throws RepositoryException, SQLException;

	Issue addIssue(String title, String description, boolean openIssue, int workItemId) throws RepositoryException, SQLException;

	int updateIssue(int userId, String column, String newValue) throws RepositoryException, SQLException;

}