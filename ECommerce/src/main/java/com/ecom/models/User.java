package com.ecom.models;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer userId;
	
	@NotBlank @NotNull @NotEmpty @Size(min=3,max=20,message="Name should be atleast 3 character")
	private String name;
	
	@NotNull @Pattern(regexp="[0-9]{10}",message="Mobile number should be of 10 digits")
	private String mobile;
	
	@NotBlank @NotEmpty
	private String address;
	
	@JsonIgnore
	private String userType="User";
	
	@Email(message="Invalid Email Address")
	private String email;
	
	@NotNull @NotBlank @NotEmpty
	private String password;

}
