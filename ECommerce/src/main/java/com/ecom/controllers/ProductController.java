package com.ecom.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.exceptions.ProductException;
import com.ecom.models.Product;
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
	
	@PutMapping(value="/updateProduct")
	public ResponseEntity<Product> updateProduct(@Valid @RequestBody Product product) throws ProductException{
		
		Product prod=pService.updateProduct(product);
		
		return new ResponseEntity<>(prod,HttpStatus.ACCEPTED);
		
		
	}
	
	@GetMapping(value="/viewProduct/{pId}")
	public ResponseEntity<Product> viewProduct(@Valid @PathVariable Integer pId) throws ProductException{
		
		Product prod=pService.viewProduct(pId);
		
		return new ResponseEntity<>(prod,HttpStatus.ACCEPTED);
		
		
	}
	
	@DeleteMapping(value="/deleteProduct/{pId}")
	public ResponseEntity<Product> deleteProduct(@Valid @PathVariable Integer pId) throws ProductException{
		
		Product prod=pService.removeProduct(pId);
		
		return new ResponseEntity<>(prod,HttpStatus.ACCEPTED);
		
		
	}
	
	@GetMapping(value="/viewProductByCategoryName/{cName}")
	public ResponseEntity<List<Product>> deleteProduct(@Valid @PathVariable String cName) throws ProductException{
		
		List<Product> prod=pService.viewProductByCategory(cName);
		
		return new ResponseEntity<>(prod,HttpStatus.ACCEPTED);
		
		
	}
	
	@GetMapping(value="/viewProductAll")
	public ResponseEntity<List<Product>> viewAllProduct() throws ProductException{
		
		List<Product> prod=pService.viewAllProducts();
		
		return new ResponseEntity<>(prod,HttpStatus.ACCEPTED);
		
		
	}
}
