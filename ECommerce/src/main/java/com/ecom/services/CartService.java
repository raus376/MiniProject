package com.ecom.services;

import java.util.List;

import com.ecom.exceptions.CartException;
import com.ecom.models.Cart;
import com.ecom.models.Product;

public interface CartService {
	
    public Cart addProductToCart(Integer productId, Integer quantity) throws CartException;
	
	public List<Product> removeProductFromCart(Integer productId) throws CartException;
	
	
	public Cart removeAllProducts() throws CartException;
	
	public List<Product> viewAllProducts()  throws CartException;

}
