package com.onboarding.projects.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onboarding.projects.entity.Follow;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long>{
	
		List<Follow> findByFollower_UserId(Long followerId);
		
		Optional<Follow> findByFollower_UserIdAndFollowee_UserId(Long followerId, Long followeeId);

		boolean existsByFollower_UserIdAndFollowee_UserId(Long followerId, Long followeeId);
		
}
