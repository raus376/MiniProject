package com.ecom.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<MyErrorDetails> AccessDeniedException(AccessDeniedException ae,WebRequest wb){
		
		MyErrorDetails authEx=new MyErrorDetails();
		authEx.setTimeStamp(LocalDateTime.now());
		authEx.setMessage(ae.getMessage());
		authEx.setDetails(wb.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(authEx,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyErrorDetails> methodArgumentNotValidException(MethodArgumentNotValidException ae,WebRequest wb){
		
		MyErrorDetails authEx=new MyErrorDetails();
		authEx.setTimeStamp(LocalDateTime.now());
		authEx.setMessage(ae.getMessage());
		authEx.setDetails(wb.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(authEx,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CartException.class)
	public ResponseEntity<MyErrorDetails> cartException(CartException ae,WebRequest wb){
		
		MyErrorDetails authEx=new MyErrorDetails();
		authEx.setTimeStamp(LocalDateTime.now());
		authEx.setMessage(ae.getMessage());
		authEx.setDetails(wb.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(authEx,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ProductException.class)
	public ResponseEntity<MyErrorDetails> productException(ProductException ae,WebRequest wb){
		
		MyErrorDetails authEx=new MyErrorDetails();
		authEx.setTimeStamp(LocalDateTime.now());
		authEx.setMessage(ae.getMessage());
		authEx.setDetails(wb.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(authEx,HttpStatus.BAD_REQUEST);
	}
	
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetails> otherException(Exception ae,WebRequest wb){
		
		MyErrorDetails authEx=new MyErrorDetails();
		authEx.setTimeStamp(LocalDateTime.now());
		authEx.setMessage(ae.getMessage());
		authEx.setDetails(wb.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(authEx,HttpStatus.BAD_REQUEST);
	}
}
