package com.onboarding.projects.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onboarding.projects.dto.FollowResponseDto;
import com.onboarding.projects.entity.Follow;
import com.onboarding.projects.exception.UserNotFoundException;
import com.onboarding.projects.repository.FollowRepository;
import com.onboarding.projects.repository.UserRepository;
import com.onboarding.projects.util.EntityDtoMapper;

@Service
public class FollowService {
		private final FollowRepository followRepository;
		private final UserRepository userRepository;
		
		@Autowired
		public FollowService(FollowRepository followRepository, UserRepository userRepository) {
				this.followRepository = followRepository;
				this.userRepository = userRepository;
		}

		@Transactional
		public FollowResponseDto follow(Long followerId, Long followeeId) {
				
				if (followerId.equals(followeeId)) {
					throw new IllegalArgumentException("자신을 팔로우 할 수 없습니다.");
				}
				
				userRepository.findById(followerId)
												.orElseThrow(() -> new UserNotFoundException(
														String.format("팔로어 사용자(%d)를 찾을 수 없습니다.", followerId)));
				
				userRepository.findById(followeeId)
												.orElseThrow(() -> new UserNotFoundException(
														String.format("팔로잉 사용자(%d)를 찾을 수 없습니다.", followeeId)));

				if (followRepository.existsByFollower_UserIdAndFollowee_UserId(followerId, followeeId)) {
				    throw new IllegalArgumentException("이미 팔로우한 사용자입니다.");
				}
				
				Follow follow = followRepository.save(EntityDtoMapper.toEntity(followerId, followeeId));
				
				return EntityDtoMapper.toDto(follow);
		}

		@Transactional
		public void unfollow(Long followerId, Long followeeId) {
			
				Follow follow = followRepository.findByFollower_UserIdAndFollowee_UserId(followerId, followeeId)
																					.orElseThrow(() -> new UserNotFoundException(
																							String.format("팔로어(%d)와 팔로잉(%d)을 연결하는 Follow를 찾을 수 없습니다."
																														, followerId, followeeId)));

				followRepository.delete(follow);
		}
		
		@Transactional(readOnly = true)
		public List<FollowResponseDto> getFollowList(Long userId) {
			
			userRepository.findById(userId)
										    .orElseThrow(() -> new UserNotFoundException(
										        String.format("해당 사용자(%d)를 찾을 수 없습니다.", userId)));
			
				return followRepository.findByFollower_UserId(userId).stream()
								.map(EntityDtoMapper::toDto).toList();
		}
		
}
