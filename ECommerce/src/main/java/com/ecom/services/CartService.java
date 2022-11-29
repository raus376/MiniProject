package com.ecom.services;

import java.util.List;

import com.ecom.exceptions.CartException;
import com.ecom.models.Cart;
import com.ecom.models.Product;

public interface CartService {
	
    public Cart addProductToCart(Integer productId, int quantity,String authkey) throws CartException;
	
	public List<Product> removeProductFromCart(Integer productId,String authkey) throws CartException;
	
	public List<Product> updateProductQuantity(Integer productId,Integer quantity,String authkey) throws CartException;
	
	public Cart removeAllProducts(String key) throws CartException;
	
	public List<Product> viewAllProducts(String key)  throws CartException;

}
