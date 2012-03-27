package kame.chap13.service;

public class MessageNotFoundException extends Exception {
	
	public MessageNotFoundException(String message, Exception cause) {
		super(message, cause);
	}
	
	public MessageNotFoundException(String message) {
		super(message);
	}
}