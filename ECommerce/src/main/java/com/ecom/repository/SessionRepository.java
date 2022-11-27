package com.ecom.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecom.exceptions.InvalidCredentialException;
import com.ecom.models.CurrentUserLoginSession;

@Repository
public interface SessionRepository extends JpaRepository<CurrentUserLoginSession,Integer>{

	public Optional<CurrentUserLoginSession> findByUserId(Integer userId) throws InvalidCredentialException;

	@Query("select c from CurrentUserLoginSession c where c.authkey=?1")
	public Optional<CurrentUserLoginSession> findByAuthKey(String key);

}
