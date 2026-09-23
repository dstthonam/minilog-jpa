package com.onboarding.projects.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onboarding.projects.dto.UserRequestDto;
import com.onboarding.projects.dto.UserResponseDto;
import com.onboarding.projects.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/users/v2")
public class UserController {
		
		private final UserService userService;

		@Autowired
		public UserController(UserService userService) {
			this.userService = userService;
		}
		
		@GetMapping
		@Operation(summary = "사용자 목록 조회", description = "전체 사용자 목록 조회")
		@ApiResponses({
				@ApiResponse(responseCode = "200", description = "성공")
		})
		public ResponseEntity<List<UserResponseDto>> getUsers() {
				
				return ResponseEntity.ok(userService.getUsers());
		}
		
		@GetMapping("/{userId}")
		@Operation(summary = "사용자 조회", description = "ID로 사용자 조회")
		@ApiResponses({
				@ApiResponse(responseCode = "200", description = "성공"),
				@ApiResponse(responseCode = "404", description = "사용자 없음")
		})
		public ResponseEntity<UserResponseDto> getUserById(@PathVariable("userId") Long userId) {

			    return ResponseEntity.ok(userService.getUserById(userId));
		}

		@PostMapping
		@Operation(summary = "사용자 생성", description = "새로운 사용자 생성")
		@ApiResponses({
				@ApiResponse(responseCode = "201", description = "성공")
		})
		public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto user) {
			
				UserResponseDto createdUser = userService.createUser(user); 
			
				return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
		}

		@PutMapping("/{userId}")
		@Operation(summary = "사용자 수정", description = "ID로 사용자 수정")
		@ApiResponses({
				@ApiResponse(responseCode = "200", description = "성공"),
				@ApiResponse(responseCode = "404", description = "사용자 없음")
		})
		public ResponseEntity<UserResponseDto> updateUser(
							@PathVariable("userId") Long userId
							, @RequestBody UserRequestDto updatedUser) {
				
				UserResponseDto user = userService.updateUser(userId, updatedUser);

				return ResponseEntity.ok(user);
		}

		@DeleteMapping("/{userId}")
		@Operation(summary = "사용자 삭제", description = "ID로 사용자 삭제")
		@ApiResponses({
				@ApiResponse(responseCode = "204", description = "사용자 삭제 성공"),
				@ApiResponse(responseCode = "404", description = "사용자 없음")
		})
		public ResponseEntity<Void> deleteUser(@PathVariable("userId") Long userId) {
				
				userService.deleteUser(userId);
				
				return ResponseEntity.noContent().build();
		}

}
