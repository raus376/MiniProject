package com.ecom.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
		
		Optional<Product> prod=pRepo.findById(product.getProductId());
		
		if(prod.isPresent()) {
			return pRepo.save(product);
		}
		throw new ProductException("product not found");
	}

	@Override
	public Product viewProduct(Integer id) throws ProductException {
	
		Optional<Product> prod=pRepo.findById(id);
		
		if(prod.isPresent()) {
			Product p= prod.get();
			return p;
		}
		throw new ProductException("product not found");
	}

	@Override
	public List<Product> viewProductByCategory(String cname) throws ProductException {
		// TODO Auto-generated method stub
		 List<Product> ans=new ArrayList<>();
	  List<Product> list =pRepo.findAll();
	  if(list.size()>0) {
		 
		  for(int i=0;i<list.size();i++) {
			  if(list.get(i).getCompanyName().equals(cname)) {
				  ans.add(list.get(i));
			  }
		  }
		 return ans; 
	  }
		throw new ProductException("Product not availble in cart");
	}

	@Override
	public Product removeProduct(Integer pid) throws ProductException {
		
	Optional<Product> prod= pRepo.findById(pid);
	
	if(prod.isPresent()) {
		Product p=prod.get();
		pRepo.delete(prod.get());
		
		return p;
	}
	throw new ProductException("Product not found");
	  
	}

	@Override
	public List<Product> viewAllProducts() throws ProductException {
		
	List<Product> products=pRepo.findAll();
	if(products.size()>0) {
		return products;
	}
	throw new ProductException("Product not found");
	}
	
	

	
	
}
