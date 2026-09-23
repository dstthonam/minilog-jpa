package com.onboarding.projects.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onboarding.projects.dto.FollowRequestDto;
import com.onboarding.projects.dto.FollowResponseDto;
import com.onboarding.projects.service.FollowService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1/follow")
public class FollowController {
		
		private final FollowService followService;

		@Autowired
		public FollowController(FollowService followService) {
			this.followService = followService;
		}
		
		@GetMapping("/following/{followId}")
		@Operation(summary = "팔로잉 목록 조회", description = "팔로잉 목록 조회")
		@ApiResponses({
				@ApiResponse(responseCode = "200", description = "성공"),
				@ApiResponse(responseCode = "404", description = "사용자 없음")
		})
		public ResponseEntity<List<FollowResponseDto>> getFollowList(
							@PathVariable("followId") Long followerId
						) {
				
				List<FollowResponseDto> follows = followService.getFollowList(followerId);
				
				return ResponseEntity.ok(follows);
		}
		
		@PostMapping
		@Operation(summary = "팔로우 추가", description = "팔로우 추가")
		@ApiResponses({
				@ApiResponse(responseCode = "201", description = "성공"),
				@ApiResponse(responseCode = "404", description = "사용자 없음")
		})
		public ResponseEntity<FollowResponseDto> follow(
							@RequestBody FollowRequestDto request
						) {
			
				Long followerId = request.getFollowerId();
				Long followeeId = request.getFolloweeId();
			
				FollowResponseDto follow = followService.follow(followerId, followeeId); 
			
				return ResponseEntity.status(HttpStatus.CREATED).body(follow);
		}

		@DeleteMapping("/{followerId}/{followeeId}")
		@Operation(summary = "언팔로우", description = "팔로우 관계 삭제")
		@ApiResponses({
				@ApiResponse(responseCode = "204", description = "언팔로우 성공"),
			    @ApiResponse(responseCode = "404", description = "팔로우 관계 없음")
		})
		public ResponseEntity<Void> unfollow(
							@PathVariable("followerId") Long followerId,
							@PathVariable("followeeId") Long followeeId
						) {
				
				followService.unfollow(followerId, followeeId);
				
				return ResponseEntity.noContent().build();
		}

}
