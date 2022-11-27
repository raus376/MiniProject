package com.ecom.models;

//import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
	
	private String email;
	
	private String password;

}
