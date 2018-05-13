package se.codepatcher.taskmanager.model;

public final class User {
	
	private final int id;
	private final String firstName;
	private final String lastName;
	private final String userName;
	private final boolean activeUser;
	private final int teamId;
	
	public User(int id, String firstName, String lastName,String userName, boolean activeUser,int teamId){
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.activeUser = activeUser;
		this.teamId = teamId;	
	}

	public int getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getUserName() {
		return userName;
	}
	
	public boolean isActiveUser() {
		return activeUser;
	}

	public int getTeamId() {
		return teamId;
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
		if (other != null && other instanceof User) {
			User otherUser = (User) other;
			return this.id == otherUser.id;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", activeUser=" + activeUser + ", teamId=" + teamId + "]";
	}
}