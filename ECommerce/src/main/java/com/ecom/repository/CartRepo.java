package com.ecom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecom.models.Cart;
import com.ecom.models.Product;

@Repository
public interface CartRepo extends JpaRepository<Cart,Integer>{

	@Query("select p from Product p where p.category.categoryName = ?1")
	public List<Product> viewByCategoryName(String cname);
}
