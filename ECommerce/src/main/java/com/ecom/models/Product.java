package com.ecom.models;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer productId;
	
	@Size(min = 3, max = 25, message = "Product Name should contain min 3 character." )
	private String productName;
	
	@Min(value = 1, message = "Please Enter a valid price")
	private Double price;
	
	
	private String color;
	

	private String companyName;
	
	@Min(value = 1, message = "Please Enter a valid quantity.")
	private Integer quantity;
	
	@Embedded
	private Category category;
	
	
	
	
	
//	@JsonIgnore
	@ManyToOne(cascade=CascadeType.ALL)
	private Cart cDetails;
}
