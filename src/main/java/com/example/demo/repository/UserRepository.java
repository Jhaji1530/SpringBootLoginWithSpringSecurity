package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>
{
	//User findByUserName(String firstName,String lastName);
	User findByUserId(String userId);
	
	User findByEmail(String email);

	User findByUsername(String username);
	
}
