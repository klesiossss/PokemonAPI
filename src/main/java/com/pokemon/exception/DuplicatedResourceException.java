package com.pokemon.exception;

public class DuplicatedResourceException extends RuntimeException {
	private static final long serialVersionUID = 5266023531583283367L;

	public DuplicatedResourceException() {
		super("Esse Recurso ja existe");
	}
	
	public DuplicatedResourceException(String message) {
		super(message);
	}

}