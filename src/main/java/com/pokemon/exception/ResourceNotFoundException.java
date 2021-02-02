package com.pokemon.exception;

public class ResourceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -6030733006897912418L;
	
	public ResourceNotFoundException() {
		super("Failed to search for resource with the attributes provided");
	}
	
	public ResourceNotFoundException(String message) {
		super(message);
	}

}

