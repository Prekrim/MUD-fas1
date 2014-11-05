package exceptionPack;


@SuppressWarnings("serial")
public class DoorException extends Exception{	
	public DoorException() { super(); }
	public DoorException(String message) { super(message); }
	public DoorException(String message, Throwable cause) { super(message, cause); }
	public DoorException(Throwable cause) { super(cause); }
}