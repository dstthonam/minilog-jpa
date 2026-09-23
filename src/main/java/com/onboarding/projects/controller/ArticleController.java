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

import com.onboarding.projects.dto.ArticleRequestDto;
import com.onboarding.projects.dto.ArticleResponseDto;
import com.onboarding.projects.service.ArticleService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1/article")
public class ArticleController {
		
		private final ArticleService articleService;

		@Autowired
		public ArticleController(ArticleService articleService) {
			this.articleService = articleService;
		}
		
		@GetMapping("/user/{authorId}")
		@Operation(summary = "사용자 게시글 목록 전체 조회", description = "해당 사용자의 게시글 목록 전체 조회")
		@ApiResponses({
				@ApiResponse(responseCode = "200", description = "성공"),
				@ApiResponse(responseCode = "404", description = "사용자 없음")
		})
		public ResponseEntity<List<ArticleResponseDto>> getArticleListByUserId(@PathVariable("authorId") Long authorId) {
			
				var articleList = articleService.getArticleListByUserId(authorId);
			
				return ResponseEntity.ok(articleList);
		}
		
		@GetMapping("/{articleId}")
		@Operation(summary = "게시글 조회", description = "ID로 게시글 조회")
		@ApiResponses({
				@ApiResponse(responseCode = "200", description = "성공"),
				@ApiResponse(responseCode = "404", description = "게시글 없음")
		})
		public ResponseEntity<ArticleResponseDto> getArticle(@PathVariable("articleId") Long articleId) {
				
				var article = articleService.getArticleById(articleId);
				
				return ResponseEntity.ok(article);
		}

		@PostMapping
		@Operation(summary = "게시글 생성", description = "새로운 게시글 생성")
		@ApiResponses({
				@ApiResponse(responseCode = "201", description = "게시글 생성 성공")
		})
		public ResponseEntity<ArticleResponseDto> createArticle(@RequestBody ArticleRequestDto article) {
			
				ArticleResponseDto createdArticle = articleService.createArticle(article); 
				
				return ResponseEntity.status(HttpStatus.CREATED).body(createdArticle);
		}

		@PutMapping("/{articleId}")
		@Operation(summary = "게시글 수정", description = "ID로 게시글 수정")
		@ApiResponses({
				@ApiResponse(responseCode = "200", description = "성공"),
				@ApiResponse(responseCode = "404", description = "게시글 없음")
		})
		public ResponseEntity<ArticleResponseDto> updateArticle(
							@PathVariable("articleId") Long articleId
							, @RequestBody ArticleRequestDto article ) {
				
				var updatedArticle = articleService.updateArticle(articleId, article);

				return ResponseEntity.ok(updatedArticle);
		}

		@DeleteMapping("/{articleId}")
		@Operation(summary = "게시글 삭제", description = "ID로 게시글 삭제")
		@ApiResponses({
				@ApiResponse(responseCode = "204", description = "삭제됨"),
				@ApiResponse(responseCode = "404", description = "게시글 없음")
		})
		public ResponseEntity<Void> deleteArticle(@PathVariable("articleId") Long articleId) {
				
				articleService.deleteArticle(articleId);
				
				return ResponseEntity.noContent().build();
		}

}
