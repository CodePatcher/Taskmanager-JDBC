package se.codepatcher.taskmanager.service;

import java.sql.SQLException;
import java.util.List;
import se.codepatcher.taskmanager.exception.RepositoryException;
import se.codepatcher.taskmanager.exception.ServiceException;
import se.codepatcher.taskmanager.model.User;
import se.codepatcher.taskmanager.model.WorkItem;
import se.codepatcher.taskmanager.repository.UserRepository;
import se.codepatcher.taskmanager.repository.UserSQLRepository;
import se.codepatcher.taskmanager.repository.WorkItemRepository;
import se.codepatcher.taskmanager.repository.WorkItemSQLRepository;

public final class WorkItemService {

	private final WorkItemRepository workItemRepository;
	private final UserRepository userRepository;
	
	public WorkItemService() {
		workItemRepository = new WorkItemSQLRepository();
		userRepository = new UserSQLRepository();
	}

	public int changeStatusByUserId(int userId, String status) throws RepositoryException, SQLException {
		return workItemRepository.changeStatusByUserId(userId, status);
	}

	public WorkItem getWorkItemById(int id) throws RepositoryException, SQLException {
		return workItemRepository.getWorkItemByID(id);
	}

	public List<WorkItem> getAllWorkItemsWithIssues() throws RepositoryException, SQLException {
		return workItemRepository.getAllWorkItemsWithIssues();
	}

	public WorkItem addWorkItem(String title, String description) throws RepositoryException, SQLException {
		return workItemRepository.addWorkItem(title, description);
	}

	public int changeStatusByWorkItemId(int workItemId, String status) throws RepositoryException, SQLException {
		return workItemRepository.changeStatusByWorkItemId(workItemId, status);
	}

	public List<WorkItem> getAllWorkItems(int page) throws RepositoryException, ServiceException, SQLException {
		if(page < 0) {
			throw new ServiceException("paging is only allowed with a value bigger or equal to zero");
		}
		return workItemRepository.getAllWorkItems(page);
	}

	public int addWorkItemToUser(int userId, int workItemId) throws RepositoryException, ServiceException, SQLException {
		User user = userRepository.getUserByID(userId);

		if (user.isActiveUser()) {
			if (workItemRepository.getAllWorkItemsOnActiveUser(user.getId()).size() < 5) {
				return workItemRepository.addWorkItemToUser(userId, workItemId);
			} else {
				throw new ServiceException("Too many workitems assigned to one user");
			}
		}
		throw new ServiceException("Cant perform operation, user is inactive.");

	}

	public List<WorkItem> getAllWorkItemsOnActiveUser(int userID) throws RepositoryException, SQLException {
		return workItemRepository.getAllWorkItemsOnActiveUser(userID);
	}

	public List<WorkItem> getAllWorkItemsByTeamId(int teamId) throws RepositoryException, SQLException {
		return workItemRepository.getAllWorkItemsByTeamId(teamId);
	}
	
	public int deleteWorkItem(int workItemId) throws RepositoryException, SQLException{
		return workItemRepository.deleteWorkItem(workItemId);
	}
	
	public List<WorkItem> getHistoryOfWorkItem(String dateFrom, String dateTo)throws RepositoryException, SQLException, ServiceException {
		if(dateFrom.length() == 10 && dateFrom.matches("([0-9]{4})-([0-9]{2})-([0-9]{2})") && 
				(dateTo.length() == 10 && dateTo.matches("([0-9]{4})-([0-9]{2})-([0-9]{2})"))) {
			return workItemRepository.getHistoryOfWorkItem(dateFrom, dateTo);
		}
			else {
				throw new ServiceException("wrong date format, must be YYYY-MM-DD");
			}
		
	}
	
}