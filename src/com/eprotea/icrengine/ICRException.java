package com.eprotea.icrengine;

public class ICRException extends Exception {
	public ICRException() {
	}
	
	public ICRException(String msg) {
		super(msg);
	}
	
	public ICRException(String msg, Throwable t) {
		super(msg, t);
	}
}
