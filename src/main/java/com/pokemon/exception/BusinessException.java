package com.pokemon.exception;

public class BusinessException extends RuntimeException {
	private static final long serialVersionUID = -1272687423112153059L;
	
	public BusinessException() {
		super("Failed to complete operation, check data provided");
	}
	
	public BusinessException(String message) {
		super(message);
	}

}
