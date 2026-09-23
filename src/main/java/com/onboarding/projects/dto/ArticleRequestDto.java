package com.onboarding.projects.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleRequestDto {
	
		@NonNull
		private String articleContent;

		@NonNull
		private String deleteFlag;
		
		@NonNull
		private Long authorId;
		
}
