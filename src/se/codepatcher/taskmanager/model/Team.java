package se.codepatcher.taskmanager.model;

public final class Team {
	
	private final int id;
	private final String teamName;
	private final int numberOfMembers;
	private final boolean activeTeam;

	public Team(int id, String teamName, int numberOfMembers, boolean activeTeam) {
		this.id = id;
		this.teamName = teamName;
		this.numberOfMembers = numberOfMembers;
		this.activeTeam = activeTeam;
		
	}

	public int getId() {
		return id;
	}

	public String getTeamName() {
		return teamName;
	}

	public int getNumberOfMembers() {
		return numberOfMembers;
	}

	public boolean isActiveTeam() {
		return activeTeam;
	}
	
	@Override
	public int hashCode(){
		final int prime = 37;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	
	@Override
	public boolean equals(Object other) {
		if (other != null && other instanceof Team) {
			Team otherUser = (Team) other;
			return this.id == otherUser.id;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "Team [id=" + id + ", teamName=" + teamName + " ,numberOfMembers=" + numberOfMembers 
				+ ", activeTeam=" + activeTeam + "]";
	}
}