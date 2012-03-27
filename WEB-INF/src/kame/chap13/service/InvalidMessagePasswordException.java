package kame.chap13.service;

public class InvalidMessagePasswordException extends Exception {
	
	public InvalidMessagePasswordException(String message, Exception cause) {
		super(message, cause);
	}
	
	public InvalidMessagePasswordException(String message) {
		super(message);
	}
}