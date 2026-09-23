package com.onboarding.projects.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onboarding.projects.dto.UserRequestDto;
import com.onboarding.projects.dto.UserResponseDto;
import com.onboarding.projects.entity.User;
import com.onboarding.projects.exception.UserNotFoundException;
import com.onboarding.projects.repository.UserRepository;
import com.onboarding.projects.util.EntityDtoMapper;

@Service
public class UserService {
		private final UserRepository userRepository;
		
		@Autowired
		public UserService(UserRepository userRepository) {
				this.userRepository = userRepository;
		}
		
		@Transactional(readOnly = true)
		public List<UserResponseDto> getUsers() {
			
				return userRepository.findAll().stream()
								.map(EntityDtoMapper::toDto)
								.collect(Collectors.toList());
		}

		@Transactional(readOnly = true)
		public UserResponseDto getUserById(Long userId) {

		    User user = userRepository.findById(userId)
															        .orElseThrow(() -> new UserNotFoundException(
															            String.format("해당 사용자(%d)를 찾을 수 없습니다.", userId)));

		    return EntityDtoMapper.toDto(user);
		}
		
		@Transactional
		public UserResponseDto createUser(UserRequestDto userRequestDto) {
			
				if (userRepository.findByUserName(userRequestDto.getUserName()).isPresent()) {
					
					throw new IllegalArgumentException("이미 존재하는 닉네임 이름입니다.");
				}
				
				User savedUser = userRepository.save(
														User.builder()
																	.userName(userRequestDto.getUserName())
																	.userPassword(userRequestDto.getUserPassword())
																	//.deleteFlag('Y') 
																	.build());
				
				return EntityDtoMapper.toDto(savedUser);
		}
		
		@Transactional
		public UserResponseDto updateUser(Long userId, UserRequestDto userRequestDto) {
				
				User user = userRepository.findById(userId)
																		.orElseThrow(() -> new UserNotFoundException(
																				String.format("해당 사용자(%s)를 찾을 수 없습니다.", userId)));
				
				// 이미 존재하는 사용자 인지 조회
				Optional<User> existingUser = userRepository.findByUserName(userRequestDto.getUserName());
				
				if (existingUser.isPresent()
							&& !existingUser.get().getUserId().equals(userId)) { // 아이디는 수정하지 않고 다른 정보만 바꿀 떄 통과

					throw new IllegalArgumentException(
							String.format("해당 사용자 아이디는(%s) 사용할 수 없습니다.",userId));
				}
				
				user.updateUser(userRequestDto.getUserName()	// UserName 변경 가능
													, userRequestDto.getUserPassword() // Password 변경 가능
													, userRequestDto.getDeleteFlag()); // 사용자 탈퇴 가능
				
				return EntityDtoMapper.toDto(user);
		}

		@Transactional
		public void deleteUser(Long userId) {

				userRepository.findById(userId)
												.orElseThrow(() -> new UserNotFoundException(
														String.format("해당 사용자(%s)를 찾을 수 없습니다.", userId)));
				
				userRepository.deleteById(userId);
		}
		
}
