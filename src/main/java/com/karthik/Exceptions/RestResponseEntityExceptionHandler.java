package com.karthik.Exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
/**
 * 
 * Annotation, to handle the exceptions globally.
 *
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(HttpStatusCodeException.class)
	protected ResponseEntity<Object> handleConflict(HttpStatusCodeException ex, WebRequest request) {
		
		String responseBody = "";
		
		int statusCode = ex.getRawStatusCode();
		
		if (statusCode == 404) {
			
			responseBody = "Repository for this owner is not found";
			
		} else if (statusCode == 500) {
			
			responseBody = "Something wrong in github server";
			
		} else {
			
			responseBody = "unable to process the contained instructions";
			
		}
		
		return handleExceptionInternal(ex, responseBody, new HttpHeaders(), HttpStatus.resolve(statusCode), request);

	}
}