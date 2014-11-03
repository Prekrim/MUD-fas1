package exceptionPack;

@SuppressWarnings("serial")
public class InventoryException extends Exception {
	  public InventoryException() { super(); }
	  public InventoryException(String message) { super(message); }
	  public InventoryException(String message, Throwable cause) { super(message, cause); }
	  public InventoryException(Throwable cause) { super(cause); }
	}
