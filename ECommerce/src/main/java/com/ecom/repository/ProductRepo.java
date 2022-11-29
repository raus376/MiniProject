package com.ecom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ecom.models.Product;

public interface ProductRepo extends JpaRepository<Product,Integer>{

	@Query("select p from Product p where p.category.categoryName = ?1")
	public List<Product> viewByCategoryName(String cname);
	
}
