package se.codepatcher.taskmanager.model;

public final class Issue {

	private final int id;
	private final String title;
	private final String description;
	private final boolean openIssue;
	private final int workItemId;
	
	public Issue(int id, String title, String description, boolean openIssue, int workItemId) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.openIssue = openIssue;
		this.workItemId = workItemId;
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

	public boolean isOpenIssue() {
		return openIssue;
	}

	public int getWorkItemId() {
		return workItemId;
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
		if (other != null && other instanceof Issue) {
			Issue otherUser = (Issue) other;
			return this.id == otherUser.id;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Issue [id=" + id + ", title=" + title + ", description=" + description + ", openIssue=" + openIssue
				+ ", workItemId=" + workItemId + "]";
	}
	
}