package com.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.models.Cart;

@Repository
public interface CartRepo extends JpaRepository<Cart,Integer>{

}
