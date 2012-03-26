package kame.chap13.service;

public class serviceException extends exception {
	
	public ServiceException(String message, Exception cause) {
		super(message, cause);
	}
	
	public ServiceException(String message) {
		super(message);
	}
}