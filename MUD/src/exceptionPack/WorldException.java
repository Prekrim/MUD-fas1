package exceptionPack;


public class WorldException extends Exception {
	  public WorldException() { super(); }
	  public WorldException(String message) { super(message); }
	  public WorldException(String message, Throwable cause) { super(message, cause); }
	  public WorldException(Throwable cause) { super(cause); }
	}
