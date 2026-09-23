package com.onboarding.projects.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onboarding.projects.dto.ArticleResponseDto;
import com.onboarding.projects.service.ArticleService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1/feed")
public class FeedController {
		
		private final ArticleService articleService;

		@Autowired
		public FeedController(ArticleService articleService) {
			this.articleService = articleService;
		}
		
		@GetMapping("/{followerId}")
		@Operation(summary = "피드 조회", description = "피드 조회")
		@ApiResponses({
				@ApiResponse(responseCode = "200", description = "성공"),
				@ApiResponse(responseCode = "404", description = "사용자 없음")
		})
		public ResponseEntity<List<ArticleResponseDto>> getFeedList(
		        			@PathVariable("followerId") Long followerId
						) {
				
				List<ArticleResponseDto> feedList = articleService.getFeedListByFollowerId(followerId);
			
				return ResponseEntity.ok(feedList);
		}
		
}
