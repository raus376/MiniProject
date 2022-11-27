package com.ecom.controllers;

import java.time.LocalDateTime;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.exceptions.InvalidCredentialException;
import com.ecom.exceptions.UserAlreadyExists;
import com.ecom.models.MessageDTO;
import com.ecom.models.SessoinDTO;
import com.ecom.models.User;
import com.ecom.models.UserDTO;
import com.ecom.services.UserAuthenticationService;



@RestController
public class UserController {
	
	@Autowired
	private UserAuthenticationService uService;
	
	@PostMapping(value="/signup")
	public ResponseEntity<MessageDTO> userSignUp(@Valid @RequestBody User user) throws UserAlreadyExists{
		
		User u=uService.userSignUp(user);
		MessageDTO message=new MessageDTO();
		if(u!=null) {
			message.setMessage("Register successfully");
		    message.setTStamp(LocalDateTime.now());
		}
		
		return new ResponseEntity<MessageDTO>(message,HttpStatus.ACCEPTED);
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<SessoinDTO> userSignIn(@Valid @RequestBody UserDTO user) throws InvalidCredentialException{
		
		SessoinDTO sdt=uService.userLogin(user);
		
		return new ResponseEntity<SessoinDTO>(sdt,HttpStatus.OK);
	}
	
	@PostMapping("/logout")
	public ResponseEntity<MessageDTO> userLogout(@RequestParam(value = "key") String authKey)throws InvalidCredentialException{
		MessageDTO message = new MessageDTO(); 
		
		String msg = uService.userLogout(authKey);
		message.setMessage(msg);
		message.setTStamp(LocalDateTime.now());
		
		return new ResponseEntity<MessageDTO>(message,HttpStatus.OK);
	}
	
	@PutMapping("/profile")
	public ResponseEntity<String> updateUser(@Valid @RequestBody User user)throws InvalidCredentialException{
		 uService.updateUser(user);
		return new ResponseEntity<String>("User updated successfully...",HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<User> deleteUser(@RequestParam Integer userid,@RequestParam String authKey)throws InvalidCredentialException{
		User u = uService.deleteUser(userid, authKey);
		return new ResponseEntity<User>(u,HttpStatus.OK);
	}
	
	@PostMapping("/appoint")
	public ResponseEntity<User> appointNewAdmin(@RequestParam("email") String email,@RequestParam("code") String passcode)throws InvalidCredentialException{
		User user = uService.makeAdmin(email, passcode);
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	

}
