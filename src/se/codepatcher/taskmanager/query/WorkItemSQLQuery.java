package se.codepatcher.taskmanager.query;

public final class WorkItemSQLQuery {

	public static final String GET_WORK_ITEMS_WITH_OPEN_ISSUES = "SELECT DISTINCT wi.* FROM work_item wi "
			+ "JOIN issue i ON  wi.id = i.work_item_id WHERE i.open_issue = 1;";
	public static final String GET_WORK_ITEM_BY_ID = "SELECT * FROM WORK_ITEM WHERE ID = ?";
	public static final String GET_ALL_WORK_ITEMS = "SELECT * FROM WORK_ITEM LIMIT ?,?";
	public static final String ADD_WORK_ITEM = "INSERT INTO work_item VALUES (NULL,?,?,'Unstarted',NULL,NULL)";
	public static final String CHANGE_STATUS_BY_WORK_ITEM_ID = "UPDATE work_item SET current_status = ? WHERE work_item.id = ?";
	public static final String CHANGE_STATUS_BY_USER_ID = "UPDATE work_item SET current_status = ? WHERE user_id = ?";
	public static final String ASSIGN_WORK_ITEM_TO_USER = "UPDATE work_item SET user_id = ? WHERE id = ?";
	public static final String GET_ALL_WORK_ITEMS_ON_ACTIVE_USER = "SELECT * FROM work_item WHERE user_id = ? AND current_status != 'Archive'";
	public static final String GET_ALL_WORKITEMS_BY_TEAM_ID = "SELECT wi.*  FROM work_item wi JOIN users u ON wi.user_id = u.id WHERE u.team_id = ?";
	public static final String DELETE_WORK_ITEM_BY_ID = "DELETE FROM work_item WHERE id = ?"; 
	public static final String GET_WORKITEM_BY_TIME_HISTORY = "SELECT * FROM work_item WHERE `date_of_completion` BETWEEN ? AND ? AND `current_status` = 'Done'"; 
		
}