package com.pokemon.exception;

public class DuplicatedResourceException extends RuntimeException {
	private static final long serialVersionUID = 5266023531583283367L;

	public DuplicatedResourceException() {
		super("Failed to save. Resource already exists");
	}
	
	public DuplicatedResourceException(String message) {
		super(message);
	}

}