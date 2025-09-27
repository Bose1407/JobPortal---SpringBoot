package com.bose.projects.exceptions;

public class JobNotFoundException extends RuntimeException {
	public JobNotFoundException(String msg) {
		super(msg);
	}
}
