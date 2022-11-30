package com.ecom.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.exceptions.CartException;
import com.ecom.models.Cart;
import com.ecom.models.Product;
import com.ecom.services.CartService;
import com.ecom.services.ProductService;

@RestController
public class CartController {
	
	@Autowired
	private CartService cService;
	
	@Autowired
	private ProductService pService;
	
	@PostMapping("/increaseProductToCart/{pId}/{q}")
	public ResponseEntity<Cart> increaseProductToCart(@PathVariable Integer pId,@PathVariable Integer q) throws CartException{
		
		Cart cart=cService.addProductToCart(pId, q);
		
		return new ResponseEntity<>(cart,HttpStatus.ACCEPTED);
	}

	@PostMapping("/removeProductToCart/{pId}")
	public ResponseEntity<Product> removeProductToCart(@PathVariable Integer pId) throws CartException{
		
		Product cart=cService.removeProductFromCart(pId);
		
		return new ResponseEntity<>(cart,HttpStatus.ACCEPTED);
	}
}
