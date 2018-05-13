package se.codepatcher.taskmanager.repository;

import java.sql.SQLException;
import java.util.List;
import se.codepatcher.taskmanager.exception.RepositoryException;
import se.codepatcher.taskmanager.model.WorkItem;
import se.codepatcher.taskmanager.query.WorkItemSQLQuery;

public final class WorkItemSQLRepository implements WorkItemRepository {

	@Override
	public WorkItem getWorkItemByID(int id) throws RepositoryException, SQLException {
		String query = WorkItemSQLQuery.GET_WORK_ITEM_BY_ID;

		List<WorkItem> userList = getHelper(query).addParameter(id).executeQuery(
				r -> new WorkItem(r.getInt(1), r.getString(2), r.getString(3), r.getString(4), r.getString(5),r.getInt(6)));

		return userList.get(0);
	}

	@Override
	public List<WorkItem> getAllWorkItems(int page) throws RepositoryException, SQLException {
		return getAllWorkItems(page,10);
	}
	
	@Override
	public List<WorkItem> getAllWorkItems(int page, int size) throws RepositoryException, SQLException {

		String query = WorkItemSQLQuery.GET_ALL_WORK_ITEMS;

		List<WorkItem> workItemList = getHelper(query).addParameter(page).addParameter(size) .executeQuery(
				r -> new WorkItem(r.getInt(1), r.getString(2), r.getString(3), r.getString(4), r.getString(5),r.getInt(6)));

		return workItemList;
	}

	@Override
	public List<WorkItem> getAllWorkItemsWithIssues() throws RepositoryException, SQLException {
		String query = WorkItemSQLQuery.GET_WORK_ITEMS_WITH_OPEN_ISSUES;

		List<WorkItem> issuelist = getHelper(query).executeQuery(
				r -> new WorkItem(r.getInt(1), r.getString(2), r.getString(3), r.getString(4), r.getString(5),r.getInt(6)));

		return issuelist;
	}

	@Override
	public WorkItem addWorkItem(String title, String description) throws RepositoryException, SQLException {
		String query = WorkItemSQLQuery.ADD_WORK_ITEM;

		List<Integer> genereatedId = getHelper(query).addParameter(title).addParameter(description)
				.executeUpdate(r -> r.getInt(1));

		return new WorkItem(genereatedId.get(0), title, description, "Unstarted", null,-1);
	}

	@Override 
	public int changeStatusByWorkItemId(int workItemId, String status) throws RepositoryException, SQLException {
		String query = WorkItemSQLQuery.CHANGE_STATUS_BY_WORK_ITEM_ID;

		int rowsAffected = getHelper(query).addParameter(status).addParameter(workItemId).executeUpdate();

		return rowsAffected;
	}

	@Override 
	public int changeStatusByUserId(int userId, String status) throws RepositoryException, SQLException {
		String query = WorkItemSQLQuery.CHANGE_STATUS_BY_USER_ID;

		int rowsAffected = getHelper(query).addParameter(status).addParameter(userId).executeUpdate();

		return rowsAffected;
	}
	
	@Override
	public int addWorkItemToUser(int userId, int workItemId) throws RepositoryException, SQLException {
		String query = WorkItemSQLQuery.ASSIGN_WORK_ITEM_TO_USER;

		int rowsAffected = getHelper(query).addParameter(userId).addParameter(workItemId).executeUpdate();

		return rowsAffected;
	}

	@Override
	public List<WorkItem> getAllWorkItemsOnActiveUser(int userId) throws RepositoryException, SQLException {
		String query = WorkItemSQLQuery.GET_ALL_WORK_ITEMS_ON_ACTIVE_USER;

		List<WorkItem> workItemList = getHelper(query).addParameter(userId).executeQuery(
				r -> new WorkItem(r.getInt(1), r.getString(2), r.getString(3), r.getString(4), r.getString(5),r.getInt(6)));

		return workItemList;
	}

	@Override
	public List<WorkItem> getAllWorkItemsByTeamId(int teamId) throws RepositoryException, SQLException {
		String query = WorkItemSQLQuery.GET_ALL_WORKITEMS_BY_TEAM_ID;
		List<WorkItem> workItemList = getHelper(query).addParameter(teamId).executeQuery(
				r -> new WorkItem(r.getInt(1), r.getString(2), r.getString(3), r.getString(4),r.getString(5),r.getInt(6)));
		return workItemList;
	}
	
	@Override 
	public int deleteWorkItem(int workItemId) throws RepositoryException, SQLException {
		String query = WorkItemSQLQuery.DELETE_WORK_ITEM_BY_ID;

		int rowsAffected = getHelper(query).addParameter(workItemId).executeUpdate();

		return rowsAffected;
	}
	
	@Override 
	public List<WorkItem> getHistoryOfWorkItem(String dateFrom, String dateTo) throws RepositoryException, SQLException {
		String query = WorkItemSQLQuery.GET_WORKITEM_BY_TIME_HISTORY;

		List<WorkItem> workItemList = getHelper(query).addParameter(dateFrom).addParameter(dateTo).executeQuery(
				r -> new WorkItem(r.getInt(1), r.getString(2), r.getString(3), r.getString(4), r.getString(5),r.getInt(6)));

		return workItemList;
	}

	private SQLHelper getHelper(String query) throws SQLException {
		return new SQLHelper(query);
	}

}