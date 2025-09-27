package com.bose.projects.globalexceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bose.projects.exceptions.FailedToApplyJobException;
import com.bose.projects.exceptions.JobNotFoundException;
import com.bose.projects.exceptions.RecruiterCreateException;
import com.bose.projects.exceptions.RecruiterNotFoundException;
import com.bose.projects.exceptions.SeekerCreateException;
import com.bose.projects.exceptions.SeekerNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(SeekerCreateException.class)
	public ResponseEntity<?> handleSeekerCreateException(SeekerCreateException ex){
		return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(ex.getMessage());
	}
	
	@ExceptionHandler(RecruiterCreateException.class)
	public ResponseEntity<?> handleRecruiterCreateException(RecruiterCreateException ex){
		return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(ex.getMessage());
	}
	
	@ExceptionHandler(RecruiterNotFoundException.class)
	public ResponseEntity<?> handleRecruiterNotFoundException(RecruiterNotFoundException ex){
		return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(ex.getMessage());
	}
	
	@ExceptionHandler(JobNotFoundException.class)
	public ResponseEntity<?> handleJobNotFoundException(JobNotFoundException ex){
		return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(ex.getMessage());
	}
	
	@ExceptionHandler(SeekerNotFoundException.class)
	public ResponseEntity<?> handleSeekerNotFoundException(SeekerNotFoundException ex){
		return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(ex.getMessage());
	}
	
	@ExceptionHandler(FailedToApplyJobException .class)
	public ResponseEntity<?> handleFailedToApplyJobException (FailedToApplyJobException  ex){
		return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(ex.getMessage());
	}
	

}
