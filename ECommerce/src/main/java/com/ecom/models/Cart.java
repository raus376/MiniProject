package com.ecom.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer cartId;

////	@JoinColumn(name= "customer_id", referencedColumnName = "customerId")
//	@OneToOne(cascade = CascadeType.ALL)
//	private User user;
	
//	@JoinTable(name = "carts_product", joinColumns = @JoinColumn(name="cart_id", referencedColumnName = "cartId"))
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL,mappedBy="cDetails")
	private List<Product> products = new ArrayList<>();
}
