package com.exception;


import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/*This will handle the valditions which are on DTOs but later it will handle all the exceptions globally*/
@RestControllerAdvice
public class ValidationHandler extends ResponseEntityExceptionHandler{
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
			HttpHeaders httpHeaders, HttpStatusCode httpStatus, WebRequest webRequest) {
		Map<String, Object> objectBody = new LinkedHashMap<>();
		objectBody.put("Current Timestamp", new Date());
		objectBody.put("Status", httpStatus.value());

		// Get all errors
		List<String> exceptionalErrors = exception.getBindingResult()
				.getFieldErrors()
				.stream()
				.map(x -> x.getDefaultMessage())
				.collect(Collectors.toList());

		objectBody.put("Errors", exceptionalErrors);

		return new ResponseEntity<>(objectBody, httpStatus);
	}
}

