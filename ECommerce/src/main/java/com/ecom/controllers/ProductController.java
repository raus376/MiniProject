package com.ecom.controllers;

import java.time.LocalDateTime;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.exceptions.ProductException;
import com.ecom.models.MessageDTO;
import com.ecom.models.Product;
import com.ecom.models.User;
import com.ecom.services.ProductService;
import com.ecom.services.UserAuthenticationService;

@RestController
public class ProductController {

	@Autowired
	private ProductService pService;
	
	
	@Autowired
	private UserAuthenticationService uService;
	
	
	
	@PostMapping(value="/addProduct")
	public ResponseEntity<Product> addProduct(@Valid @RequestBody Product product) throws ProductException{
		
		Product prod=pService.addProduct(product);
		
		return new ResponseEntity<>(prod,HttpStatus.ACCEPTED);
		
		
	}
}
