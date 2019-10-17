package com.sri.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.sri.exception.NotFoundException;
import com.sri.util.model.ErrorResponseModel;

/**
 * The global exception handler for the backend
 */
@ControllerAdvice
public class AggregatedExceptionHandler {

	//Not found exception
	@ExceptionHandler
	public ResponseEntity<ErrorResponseModel> handleException(NotFoundException e) {
		ErrorResponseModel error = new ErrorResponseModel(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	//All other exceptions
	@ExceptionHandler
	public ResponseEntity<ErrorResponseModel> handleException(Exception e) {
		ErrorResponseModel error = new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

}
