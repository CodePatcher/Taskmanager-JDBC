package se.codepatcher.taskmanager.exception;

public class RepositoryException extends Exception {

	private static final long serialVersionUID = 4127290146827918661L;

	public RepositoryException(String message, Throwable cause) {
		super(message, cause);
	}

	public RepositoryException(Throwable cause) {
		super(cause);
	}

	public RepositoryException(String message) {
		super(message);
	}

}
