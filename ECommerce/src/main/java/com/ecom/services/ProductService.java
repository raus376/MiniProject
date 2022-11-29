package com.ecom.services;

import java.util.List;

import com.ecom.exceptions.ProductException;
import com.ecom.models.Product;

public interface ProductService {

	
	
	public Product addProduct(Product product) throws ProductException;
	
	public Product updateProduct(Product product) throws ProductException;
	
	public Product viewProduct(Integer id) throws ProductException;
	
	public List<Product> viewProductByCategory(String cname) throws ProductException;
	
	public Product removeProduct(Integer pid)throws ProductException;
	
	public List<Product> viewAllProducts() throws ProductException;
	
}
