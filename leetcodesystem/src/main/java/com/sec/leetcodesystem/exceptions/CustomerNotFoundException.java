package com.sec.leetcodesystem.exceptions;

public class CustomerNotFoundException extends Exception{

	private static final long serialVersionUID = 1L;
	public  CustomerNotFoundException(String errorMessage) {
		
		super(errorMessage);
	}
	@Override
	public String toString() {
		return " CustomerNotFoundException " + super.getMessage();
	}

}
