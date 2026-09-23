package com.onboarding.projects.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onboarding.projects.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
		Optional<User> findByUserName(String userName);
}
