package com.example.demo.exceptions;

import java.sql.SQLException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.hibernate.exception.GenericJDBCException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class TriggerErrorHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler({SQLException.class, GenericJDBCException.class, JpaSystemException.class})
	public ResponseEntity<?> handleTriggerException(Exception ex, NativeWebRequest request) {
		String bodyOfResponse = ExceptionUtils.getRootCause(ex).getMessage();
        return handleExceptionInternal(ex, bodyOfResponse, 
          new HttpHeaders(), HttpStatus.CONFLICT, request);
	}
}
