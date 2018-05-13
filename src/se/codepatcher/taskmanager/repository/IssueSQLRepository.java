package se.codepatcher.taskmanager.repository;

import java.sql.SQLException;
import java.util.List;
import se.codepatcher.taskmanager.exception.RepositoryException;
import se.codepatcher.taskmanager.model.Issue;
import se.codepatcher.taskmanager.query.IssueSQLQuery;

public final class IssueSQLRepository implements IssueRepository {

	@Override
	public Issue getIssueById(int id) throws RepositoryException, SQLException {
		String query = IssueSQLQuery.GET_ISSUE_BY_ID;

		List<Issue> issueList = getHelper(query).addParameter(id).executeQuery(
				r -> new Issue(r.getInt(1), r.getString(2), r.getString(3), r.getBoolean(4), r.getInt(5)));

		return issueList.get(0);
	}

	@Override
	public List<Issue> getAllIssues(int page) throws RepositoryException, SQLException {
		return getAllIssues(page,10);}
	
	@Override
	public List<Issue> getAllIssues(int page, int size) throws RepositoryException, SQLException {
		String query = IssueSQLQuery.GET_ISSUES;

		List<Issue> issueList = getHelper(query).addParameter(page).addParameter(size).executeQuery(
				r -> new Issue(r.getInt(1), r.getString(2), r.getString(3), r.getBoolean(4), r.getInt(5)));
		return issueList;
	}

	@Override
	public Issue addIssue(String title, String description, boolean openIssue, int workItemId)
			throws RepositoryException, SQLException {
		String query = IssueSQLQuery.INSERT_ISSUE;

		List<Integer> genereatedId = getHelper(query).addParameter(title).addParameter(description)
				.addParameter(openIssue).addParameter(workItemId).executeUpdate(r -> r.getInt(1));

		return new Issue(genereatedId.get(0), title, description, openIssue, workItemId);
	}

	@Override
	public int updateIssue(int userId, String column, String newValue) throws RepositoryException, SQLException {
		String query = null;
		int rowsAffected = 0;

		switch (column) {
		case "title":
			query = IssueSQLQuery.UPDATE_TITLE;
			rowsAffected = getHelper(query).addParameter(newValue).addParameter(userId).executeUpdate();
			return rowsAffected;
		case "description":
			query = IssueSQLQuery.UPDATE_DESCRIPTION;
			rowsAffected = getHelper(query).addParameter(newValue).addParameter(userId).executeUpdate();
			return rowsAffected;
		case "openIssue":
			boolean state;
			if (newValue.equalsIgnoreCase("true")) {
				state = true;
			} else if (newValue.equalsIgnoreCase("false")) {
				state = false;
			} else {
				throw new RepositoryException("invalid update status type");
			}
			query = IssueSQLQuery.UPDATE_OPEN_ISSUE;
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