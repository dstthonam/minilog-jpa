package com.onboarding.projects.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@Builder
public class UserResponseDto {
	
		@NonNull
		private Long userId;
		
		@NonNull
		private String userName;

		@NonNull
		private String deleteFlag;
		
		private Long createdBy;
		
		private LocalDateTime createdDate;

		private Long modifiedBy;
		
		private LocalDateTime modifiedDate;
}
