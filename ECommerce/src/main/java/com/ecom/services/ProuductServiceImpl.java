package com.ecom.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.exceptions.ProductException;
import com.ecom.models.Cart;
import com.ecom.models.Product;
import com.ecom.repository.CartRepo;
import com.ecom.repository.ProductRepo;
import com.ecom.repository.SessionRepository;
import com.ecom.repository.UserRepository;

@Service
public class ProuductServiceImpl implements ProductService{

	@Autowired
	private CartRepo cRepo;
	
	@Autowired
	private SessionRepository sRepo;
	
	@Autowired
	private UserRepository uRepo;
	
	@Autowired
	private ProductRepo pRepo;

	@Override
	public Product addProduct(Product product) throws ProductException {
		
		if(product!=null) {
		Cart c=	product.getCDetails();
		c.getProducts().add(product);
			
			return pRepo.save(product);
		}
		throw new ProductException("Product not be empty");
	}

	@Override
	public Product updateProduct(Product product) throws ProductException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product viewProduct(Integer id) throws ProductException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> viewProductByCategory(String cname) throws ProductException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product removeProduct(Integer pid) throws ProductException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> viewAllProducts() throws ProductException {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
