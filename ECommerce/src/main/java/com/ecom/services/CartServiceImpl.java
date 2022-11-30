package com.ecom.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.exceptions.CartException;
import com.ecom.models.Cart;
import com.ecom.models.Product;
import com.ecom.repository.CartRepo;
import com.ecom.repository.ProductRepo;
import com.ecom.repository.SessionRepository;
import com.ecom.repository.UserRepository;

@Service
public class CartServiceImpl implements CartService{

	@Autowired
	private CartRepo cRepo;
	
	@Autowired
	private SessionRepository sRepo;
	
	@Autowired
	private UserRepository uRepo;
	
	@Autowired
	private ProductRepo pRepo;

	@Override
	public Cart addProductToCart(Integer productId, Integer quantity) throws CartException {
		
	Optional<Product> prod=pRepo.findById(productId);
	if(prod.isPresent()) {
		
		Product p=prod.get();
		int q=p.getQuantity();
		
		q+=quantity;
		
		p.setQuantity(q);
		Product pp=prod.get();
		long total=p.getQuantity()*pp.getPrice();
		
		pRepo.save(pp);
		
		Cart cr=p.getCDetails();
		cr.setCartValue(total);
		cRepo.save(cr);
		return p.getCDetails();
	}
	throw new CartException("product not found");
	}

	@Override
	public Product removeProductFromCart(Integer productId) throws CartException {

		Optional<Product> prod=pRepo.findById(productId);
		if(prod.isPresent()) {
			
			Product p=prod.get();
			int q=p.getQuantity();
			
			q-=1;
			
			p.setQuantity(q);
			Product pp=prod.get();
			long total=p.getQuantity()*pp.getPrice();
			
			pRepo.save(pp);
			
			Cart cr=p.getCDetails();
			cr.setCartValue(total);
			cRepo.save(cr);
			return prod.get();
		}
		throw new CartException("product not found");
	}

	@Override
	public Cart removeAllProducts() throws CartException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> viewAllProducts(Integer cId) throws CartException {

         Optional<Cart> list=   cRepo.findById(cId);
         
         if(list.isPresent()) {
        	 List<Product> pList=list.get().getProducts();
        	 return pList;
         }
         throw new CartException("Product not present");
	}
	
	
}
