package com.ecom.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class AuthenticationExceptionHandler {
	
	@ExceptionHandler(InvalidCredentialException.class)
	public ResponseEntity<MyErrorDetails> InvalidCredentialException(InvalidCredentialException ice,WebRequest wb){
		MyErrorDetails authEx=new MyErrorDetails();
		authEx.setTimeStamp(LocalDateTime.now());
		authEx.setMessage(ice.getMessage());
		authEx.setDetails(wb.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(authEx,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UserAlreadyExists.class)
	public ResponseEntity<MyErrorDetails> DuplicateSignUpExceptionHandler(UserAlreadyExists ue,WebRequest wb){
		MyErrorDetails authEx=new MyErrorDetails();
		authEx.setTimeStamp(LocalDateTime.now());
		authEx.setMessage(ue.getMessage());
		authEx.setDetails(wb.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(authEx,HttpStatus.BAD_REQUEST);
	}

}
