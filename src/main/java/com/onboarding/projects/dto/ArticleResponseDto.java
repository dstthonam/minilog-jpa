package com.onboarding.projects.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@Builder
public class ArticleResponseDto {
	
		@NonNull
		private Long articleId;
		
		@NonNull
		private String articleContent;

		@NonNull
		private Long authorId;

		@NonNull
		private String authorName;
		
		private Long createdBy;
		
		private LocalDateTime createdDate;

		private Long modifiedBy;
		
		// PIS 게시글 생성시 Null 값이 들어감으로 에러 발생
		//@NonNull
		private LocalDateTime modifiedDate;
		
}
