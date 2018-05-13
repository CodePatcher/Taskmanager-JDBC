package se.codepatcher.taskmanager.model;

public final class WorkItem {

	private final int id;
	private final String title;
	private final String description;
	private final String status;
	private final String date;
	private final int assignedUserId;
	
	public WorkItem(int id, String title, String description, String status, String date, int assignedUserId) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.status = status;
		this.date = date;
		this.assignedUserId = assignedUserId;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getStatus() {
		return status;
	}

	public String getDate() {
		return date;
	}

	public int getAssignedUserId() {
		return assignedUserId;
	}
	
	@Override
	public int hashCode() {
		final int prime = 37;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object other) {
		if (other != null && other instanceof WorkItem) {
			WorkItem otherUser = (WorkItem) other;
			return this.id == otherUser.id;
		}
		return false;
	}

	@Override
	public String toString() {
		return "WorkItem [id=" + id + ", title=" + title + ", description=" + description + ", status=" + status
				+ " ,date=" + date + ", assignedUserId=" + assignedUserId + "]";
	}

}