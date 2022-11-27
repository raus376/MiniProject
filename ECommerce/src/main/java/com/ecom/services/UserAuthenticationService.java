package com.ecom.services;

import com.ecom.exceptions.InvalidCredentialException;
import com.ecom.exceptions.UserAlreadyExists;
import com.ecom.models.SessoinDTO;
import com.ecom.models.User;
import com.ecom.models.UserDTO;

public interface UserAuthenticationService {
	
	public User userSignUp(User user) throws UserAlreadyExists;
	
	public SessoinDTO userLogin(UserDTO user) throws InvalidCredentialException;

	public String userLogout(String authKey) throws InvalidCredentialException;
	
	public boolean updateUser(User user) throws InvalidCredentialException;
	
	public User deleteUser(Integer userId,String authKey) throws InvalidCredentialException;
	
	public User makeAdmin(String userEmail,String passcode) throws InvalidCredentialException;
	
	
}
