package com.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dto.Status;
import com.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
	
	public Optional<User> findByEmail(String email);
	
	public List<User> findByFirstNameStartsWith(String name);
	
	public List<User> findByLastNameStartsWith(String name);
	
	public List<User> findByStatus(Status status);

}
