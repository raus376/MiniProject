package com.ecom.services;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.exceptions.InvalidCredentialException;
import com.ecom.exceptions.UserAlreadyExists;
import com.ecom.models.CurrentUserLoginSession;
import com.ecom.models.SessoinDTO;
import com.ecom.models.User;
import com.ecom.models.UserDTO;
import com.ecom.repository.SessionRepository;
import com.ecom.repository.UserRepository;

@Service
public class UserAuthenticationServiceImpl implements UserAuthenticationService{

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private SessionRepository sRepo;
	
	@Override
	public User userSignUp(User user) throws UserAlreadyExists {
		
	Optional<User> opt=	userRepo.findByEmail(user.getEmail());
		
	if(opt.isPresent()) {
		throw new UserAlreadyExists("User alreadyExists with userId: "+user.getUserId());
	}
	else {
		userRepo.save(user);
	}
		
	   return user;
	}

	@Override
	public SessoinDTO userLogin(UserDTO user) throws InvalidCredentialException {
		
	Optional<User> opt=	userRepo.findByEmail(user.getEmail());

	if(!opt.isPresent()) {
		throw new InvalidCredentialException("User not foun...");
	}
	   Optional<CurrentUserLoginSession> session=   sRepo.findByUserId(opt.get().getUserId());
	
	   if(session.isPresent()) {
		   throw new InvalidCredentialException("User Already LoggedIn...");
	   }
	   
	   if(!(opt.get().getEmail().equals(user.getEmail()))) {
		   throw new InvalidCredentialException("Email Incorrect...");
	   }
	   else if(!(opt.get().getPassword().equals(user.getPassword()))) {
		   throw new InvalidCredentialException("password Incorrect...");
	   }
	   else if(!(opt.get().getPassword().equals(user.getPassword()) && opt.get().getEmail().equals(user.getEmail()))){
			throw new InvalidCredentialException("Invalid Credentials...");
		}
	   
	   SessoinDTO sdt=new SessoinDTO();
	   CurrentUserLoginSession culs=new CurrentUserLoginSession();
	   String authKey=UUID.randomUUID().toString();
	   culs.setAuthkey(authKey);
	   culs.setSessionStartTime(LocalDateTime.now());
	   sdt.setAuthKey(culs.getAuthkey());
	   sdt.setSessionStartTime((culs.getSessionStartTime()));
	   culs.setUserId(opt.get().getUserId());
	   sRepo.save(culs);
	   return sdt;
	   
	}
	

	@Override
	public String userLogout(String authKey) throws InvalidCredentialException {
		
		Optional<CurrentUserLoginSession> opt=sRepo.findByAuthKey(authKey);
	
		if(opt.isPresent()) {
			sRepo.delete(opt.get());
			return "Logout Susscessfully";
		}
		else
			throw new InvalidCredentialException("Invalid Credentials...");
	}

	@Override
	public boolean updateUser(User user) throws InvalidCredentialException {
		Optional<User> checkUser = userRepo.findByEmail(user.getEmail());
		if(!checkUser.isPresent())throw new InvalidCredentialException("User doesn't exists with Id "+user.getEmail());
		User u = userRepo.save(user);
		return u!=null;
	}

	@Override
	public User deleteUser(Integer userId, String authKey) throws InvalidCredentialException {
		Optional<CurrentUserLoginSession> culs = sRepo.findByAuthKey(authKey);
		if(!culs.isPresent()) {
			throw new InvalidCredentialException("Invalid Authentication Key");
		}
		String userType = userRepo.findById(culs.get().getUserId()).get().getUserType();
		if(userType.equalsIgnoreCase("user")) {
			throw new InvalidCredentialException("Unauthorized Request...");
		}
		Optional<User> user = userRepo.findById(userId);
		if(!user.isPresent())throw new InvalidCredentialException("User doesn't exits with Id "+userId);
		userRepo.delete(user.get());
		return user.get();
	}

	@Override
	public User makeAdmin(String userEmail, String code) throws InvalidCredentialException {
		if(!code.equals("admin")) {
			throw new InvalidCredentialException("Invalid Passcode...");
		}
		else if(userEmail.equals(null)) {
			throw new InvalidCredentialException("Invalid Email Address");
		}
		Optional<User> user = userRepo.findByEmail(userEmail);
		if(!user.isPresent()) {
			throw new InvalidCredentialException("User not found with Id "+userEmail);
		}
		user.get().setUserType("admin");
		userRepo.save(user.get());
		return user.get();
	}

}
