package se.codepatcher.taskmanager.repository;

import java.sql.SQLException;
import java.util.List;
import se.codepatcher.taskmanager.exception.RepositoryException;
import se.codepatcher.taskmanager.model.WorkItem;


public interface WorkItemRepository {
	
	WorkItem getWorkItemByID(int id) throws RepositoryException, SQLException;

	List<WorkItem> getAllWorkItems(int page) throws RepositoryException, SQLException;	

	List<WorkItem> getAllWorkItems(int page, int size) throws RepositoryException, SQLException;	

	List<WorkItem> getAllWorkItemsWithIssues() throws RepositoryException, SQLException;

	WorkItem addWorkItem(String title, String description) throws RepositoryException, SQLException;

	int changeStatusByUserId(int itemId, String status) throws RepositoryException, SQLException;

	int changeStatusByWorkItemId(int workItemId, String string) throws RepositoryException, SQLException;

	int addWorkItemToUser(int userId , int workItemId) throws RepositoryException, SQLException;

	List<WorkItem> getAllWorkItemsOnActiveUser(int userId) throws RepositoryException, SQLException;
	
	List<WorkItem> getAllWorkItemsByTeamId(int teamId)throws RepositoryException, SQLException;
	
	int deleteWorkItem(int workItemId) throws RepositoryException, SQLException; 

	List<WorkItem> getHistoryOfWorkItem(String dateFrom, String dateTo) throws RepositoryException, SQLException; 
	
}