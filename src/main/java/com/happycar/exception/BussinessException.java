package com.happycar.exception;

public class BussinessException extends RuntimeException{

	public BussinessException() {
		super();
	}
	
	public BussinessException(String message) {
		super(message);
	}
}
