package se.codepatcher.taskmanager.service;

import java.sql.SQLException;
import java.util.List;
import se.codepatcher.taskmanager.exception.RepositoryException;
import se.codepatcher.taskmanager.exception.ServiceException;
import se.codepatcher.taskmanager.model.Issue;
import se.codepatcher.taskmanager.repository.IssueRepository;
import se.codepatcher.taskmanager.repository.IssueSQLRepository;
import se.codepatcher.taskmanager.repository.WorkItemRepository;
import se.codepatcher.taskmanager.repository.WorkItemSQLRepository;

public final class IssueService {

	private final WorkItemRepository workItemRepository;
	private final IssueRepository issueRepository;

	public IssueService() {
		workItemRepository = new WorkItemSQLRepository();
		issueRepository = new IssueSQLRepository();
	}	
	
	public Issue addIssue(String title, String issue, boolean openIssue, int workItemId)
			throws RepositoryException, ServiceException, SQLException {
		
		if (workItemRepository.getWorkItemByID(workItemId).getStatus().equals("Done")) {
			workItemRepository.changeStatusByWorkItemId(workItemId, "Unstarted");
			return issueRepository.addIssue(title, issue, openIssue, workItemId);
		}

		throw new ServiceException("Coundn't create issue. WorkItem need status = \"Done\"");
	}

	public int updateIssue(int issueId, String column, String newValue) throws RepositoryException, ServiceException, SQLException {
		return issueRepository.updateIssue(issueId, column, newValue);
	}

	public Issue getIssueById(int id) throws RepositoryException, SQLException {
		return issueRepository.getIssueById(id);
	}

	public List<Issue> getAllIssues(int page) throws RepositoryException, ServiceException, SQLException {
		if(page < 0) {
			throw new ServiceException("paging is only allowed with a value bigger or equal to zero");
		}
		return issueRepository.getAllIssues(page);
	}
}